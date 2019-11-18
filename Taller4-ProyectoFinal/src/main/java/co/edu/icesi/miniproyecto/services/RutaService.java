package co.edu.icesi.miniproyecto.services;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Hashtable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.daos.Tmio1RutaDao;
import co.edu.icesi.miniproyecto.exceptions.RutaDiasException;
import co.edu.icesi.miniproyecto.exceptions.RutaHorasException;
import co.edu.icesi.miniproyecto.exceptions.RutaIDRepetidoException;
import co.edu.icesi.miniproyecto.exceptions.RutaNullException;
import co.edu.icesi.miniproyecto.model.Dia;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.repositories.RutasRepository;

@Service
public class RutaService implements IRutaService {

//	private Hashtable<Dia, Integer> dias;
	private Tmio1RutaDao repos;

	@Autowired
	public RutaService(Tmio1RutaDao r) {
		repos = r;
		
//		dias = new Hashtable<Dia,Integer>();
//		dias.put(Dia.Lunes, 1);
//		dias.put(Dia.Martes, 2);
//		dias.put(Dia.Miercoles, 3);
//		dias.put(Dia.Jueves, 4);
//		dias.put(Dia.Viernes, 5);
//		dias.put(Dia.Sabado, 6);
//		dias.put(Dia.Domingo, 7);
	}

	@Override
	@Transactional
	public Tmio1Ruta agregarRuta(Tmio1Ruta ruta)
			throws RutaNullException, RutaDiasException, RutaHorasException, RutaIDRepetidoException {

		if (ruta == null) {
			throw new RutaNullException();

		} else if (ruta.getDiaInicio().longValue()>ruta.getDiaFin().longValue()) {
			throw new RutaDiasException();
		} else if (ruta.getHoraInicio().compareTo(ruta.getHoraFin()) == 1) {
			throw new RutaHorasException();
//		} else if (verificarIDRepetido(ruta.getId())) {
//			throw new RutaIDRepetidoException();
		} else {
			repos.save(ruta);
			return ruta;
		}

	}

//	private boolean verificarIDRepetido(Integer id) {	
//		return repos.findById(id)==null?false:true;
//	}

	@Override
	public Tmio1Ruta eliminarRuta(Integer id) {

		Tmio1Ruta r = consultarRuta(id);
		repos.delete(r);
		return r;
	}

	@Override
	public Tmio1Ruta consultarRuta(Integer id) {
		return repos.findById(id);
	}

	@Override
	public Tmio1Ruta actualizarRuta(Integer id, String activa, String descripcion, BigDecimal diaInicio,
			BigDecimal diaFin, BigDecimal horaInicio, BigDecimal horaFin) {
		
		Tmio1Ruta r = new Tmio1Ruta();
		r.setId(id);
		r.setActiva(activa);
		r.setDescripcion(descripcion);
		r.setDiaInicio(diaInicio);
		r.setDiaFin(diaFin);
		r.setHoraInicio(horaInicio);
		r.setHoraFin(horaFin);
		repos.update(r);
		return r;
	}
	
	
	public Iterable<Tmio1Ruta> findAllRutas(){
		return repos.findAll();
	}
//	
//	public Dia[] getAllDias() {
//		return Dia.values();
//	}

}
