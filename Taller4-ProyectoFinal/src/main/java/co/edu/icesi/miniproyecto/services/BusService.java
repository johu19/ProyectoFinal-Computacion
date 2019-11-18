package co.edu.icesi.miniproyecto.services;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.daos.Tmio1BusDao;
import co.edu.icesi.miniproyecto.exceptions.BusCapacidadException;
import co.edu.icesi.miniproyecto.exceptions.BusIDRepetidoException;
import co.edu.icesi.miniproyecto.exceptions.BusNullException;
import co.edu.icesi.miniproyecto.exceptions.BusPlacaRepetidaException;
import co.edu.icesi.miniproyecto.exceptions.BusTipoException;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.TipoUsuario;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;

import co.edu.icesi.miniproyecto.repositories.BusesRepository;

@Service
public class BusService implements IBusService {

	private Tmio1BusDao repos;

	@Autowired
	public BusService(Tmio1BusDao b) {
		repos = b;
	}

	@Override
	@Transactional
	public Tmio1Bus agregarBus(Tmio1Bus bus) throws BusNullException, BusTipoException, BusCapacidadException,
			BusPlacaRepetidaException, BusIDRepetidoException {

		if (bus == null) {
			throw new BusNullException();
		} else if (!bus.getTipo().toString().equals("T") && !bus.getTipo().toString().equals("A") && !bus.getTipo().toString().equals("P")) {
			throw new BusTipoException();
		} else if (bus.getCapacidad().compareTo(new BigDecimal(1)) == -1) {
			throw new BusCapacidadException();
//		} else if (verificarIDRepetido(bus.getId())) {
//			throw new BusIDRepetidoException();
		} else if (verificarPlacaRepetida(bus.getPlaca())) {
			throw new BusPlacaRepetidaException();

		} else {
			repos.save(bus);
			return bus;

		}

	}

	private boolean verificarPlacaRepetida(String p) {

		if (repos.findByPlaca(p).size() > 0) {
			return true;
		} else {
			return false;
		}

	}

//	private boolean verificarIDRepetido(Integer id) {			
//		return repos.findById(id).isPresent();
//	}

	@Override
	public Tmio1Bus eliminarBus(Integer id) {
		Tmio1Bus b = consultarBus(id);
		repos.delete(b);
		return b;
	}

	@Override
	public Tmio1Bus consultarBus(Integer id) {
		return repos.findById(id);
	}

	@Override
	public Tmio1Bus actualizarBus(Integer id, BigDecimal capacidad, String marca, BigDecimal modelo, String placa,
			TipoBus tipo) {

		Tmio1Bus b = new Tmio1Bus();
		b.setId(id);
		b.setCapacidad(capacidad);
		b.setMarca(marca);
		b.setModelo(modelo);
		b.setPlaca(placa);
		b.setTipo(tipo);

		repos.update(b);
		return b;
	}

	@Override
	public Iterable<Tmio1Bus> findAllBuses() {
		return repos.findAll();
	}
	
	
	public TipoBus[] obtenerTipos() {
		return TipoBus.values();
	}
	
	

}
