package co.edu.icesi.miniproyecto.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.daos.Tmio1ConductoreDao;
import co.edu.icesi.miniproyecto.exceptions.ConductorCedulaRepetidaException;
import co.edu.icesi.miniproyecto.exceptions.ConductorFechasException;
import co.edu.icesi.miniproyecto.exceptions.ConductorNullException;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.repositories.ConductoresRepository;


@Service
public class ConductorService implements IConductorService {

	
	private Tmio1ConductoreDao repos;

	@Autowired
	public ConductorService(Tmio1ConductoreDao c) {
		repos = c;
	}

	@Override
	@Transactional
	public Tmio1Conductore agregarConductor(Tmio1Conductore conductor)
			throws ConductorNullException, ConductorFechasException, ConductorCedulaRepetidaException {

		if (conductor == null) {
			throw new ConductorNullException();
		} else if (conductor.getFechaNacimiento().after(conductor.getFechaContratacion())) {
			throw new ConductorFechasException();
//		} else if (verificarCedulaRepetida(conductor.getCedula())) {
//			throw new ConductorCedulaRepetidaException();
		} else {
			repos.save(conductor);
			return conductor;
		}

	}

//	private boolean verificarCedulaRepetida(String c) {
//		
//		try {
//			if(repos.findByCedula(c).size()>0) {
//				return true;
//			}else {
//				return false;
//			}
//		} catch (Exception e) {
//			return false;
//		}
//		
//	}

	@Override
	public Tmio1Conductore eliminarConductor(String cedula) {
		Tmio1Conductore c = consultarConductor(cedula);
		repos.delete(c);
		return c;
	}

	@Override
	public Tmio1Conductore consultarConductor(String cedula) {
		return repos.findByCedula(cedula);
	}

	@Override
	public Tmio1Conductore actualizarConductor(String cedula, String apellidos, String nombre, Date fechaContratacion,
			Date fechaNacimiento) {
		Tmio1Conductore c = new Tmio1Conductore();
		c.setCedula(cedula);
		c.setApellidos(apellidos);
		c.setNombre(nombre);
		c.setFechaContratacion(fechaContratacion);
		c.setFechaNacimiento(fechaNacimiento);
		repos.update(c);
		return c;
	}
	
	
	public Iterable<Tmio1Conductore> findAllConductores(){
		
		return repos.findAll();
	}

}
