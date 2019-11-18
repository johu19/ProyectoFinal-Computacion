package co.edu.icesi.miniproyecto.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.daos.Tmio1ServicioDao;
import co.edu.icesi.miniproyecto.exceptions.BusNoRegistradoException;
import co.edu.icesi.miniproyecto.exceptions.BusNullException;
import co.edu.icesi.miniproyecto.exceptions.ConductorNoRegistradoException;
import co.edu.icesi.miniproyecto.exceptions.ConductorNullException;
import co.edu.icesi.miniproyecto.exceptions.RutaNoRegistradaException;
import co.edu.icesi.miniproyecto.exceptions.RutaNullException;
import co.edu.icesi.miniproyecto.exceptions.ServicioFechasException;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.repositories.ServiciosRepository;

@Service
public class ServicioService implements IServicioService {

	
	private Tmio1ServicioDao repos;

	
	@Autowired
	public ServicioService(Tmio1ServicioDao s, BusService b, RutaService r, ConductorService c) {
		busService =b;
		rutaService=r;
		conductorService=c;
		repos = s;
	}

	public IRutaService getRutaService() {
		return rutaService;
	}

	public void setRutaService(IRutaService rutaService) {
		this.rutaService = rutaService;
	}

	public IBusService getBusService() {
		return busService;
	}

	public void setBusService(IBusService busService) {
		this.busService = busService;
	}

	public IConductorService getConductorService() {
		return conductorService;
	}

	public void setConductorService(IConductorService conductorService) {
		this.conductorService = conductorService;
	}

	private IRutaService rutaService;

	private IBusService busService;

	private IConductorService conductorService;

	@Override
	@Transactional
	public Tmio1Servicio agregarServicio(Tmio1Servicio servicio)
			throws BusNullException, ConductorNullException, RutaNullException, BusNoRegistradoException,
			RutaNoRegistradaException, ConductorNoRegistradoException, ServicioFechasException {

		if (servicio.getTmio1Bus() == null) {
			throw new BusNullException();
		} else if (servicio.getTmio1Conductore() == null) {
			throw new ConductorNullException();
		} else if (servicio.getTmio1Ruta() == null) {
			throw new RutaNullException();
		} else if (busService.consultarBus(servicio.getTmio1Bus().getId()) == null) {
			throw new BusNoRegistradoException();

		} else if (rutaService.consultarRuta(servicio.getTmio1Ruta().getId()) == null) {
			throw new RutaNoRegistradaException();
		} else if (conductorService.consultarConductor(servicio.getTmio1Conductore().getCedula()) == null) {
			throw new ConductorNoRegistradoException();
		} else if (servicio.getFechaServicio().before(servicio.getTmio1Conductore().getFechaContratacion())) {
			throw new ServicioFechasException();
		} else {
			repos.save(servicio);
			return servicio;
		}

	}
	
	

	@Override
	public Tmio1Servicio eliminarServicio(Tmio1ServicioPK id) {
		Tmio1Servicio s = consultarServicio(id);
		repos.delete(s);
		return s;
	}

	@Override
	public Tmio1Servicio consultarServicio(Tmio1ServicioPK id) {
		return repos.findById(id);
	}

	@Override
	public Tmio1Servicio actualizarServicio(Tmio1ServicioPK id, Tmio1Ruta ruta, Tmio1Conductore conductor,
			Tmio1Bus bus) {
		Tmio1Servicio s = new Tmio1Servicio();
		s.setId(id);
		s.setTmio1Bus(bus);
		s.setTmio1Conductore(conductor);
		s.setTmio1Ruta(ruta);
		repos.update(s);
		return s;
	}
	
	public Iterable<Tmio1Servicio> findAllServicios(){
		return repos.findAll();
	}
	
	public Tmio1Servicio findByPlaneID(String pid){
		return repos.findByPlaneId(pid).get(0);
		
	}
	
	public List<Tmio1Servicio> findByDate(Date d){
		
		ArrayList<Tmio1Servicio> lista = new ArrayList<Tmio1Servicio>();
		
		Iterable<Tmio1Servicio> servs = repos.findAll();
		for(Tmio1Servicio s : servs) {
			Date fecha = s.getFechaServicio();
			if(fecha.getYear()==d.getYear() && fecha.getMonth()==d.getMonth()&& fecha.getDate()==d.getDate()) {
				lista.add(s);
			}
		}
		
		return (List<Tmio1Servicio>) lista;
	}

}
