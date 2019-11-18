package co.edu.icesi.miniproyecto.services;

import java.math.BigDecimal;

import co.edu.icesi.miniproyecto.exceptions.BusCapacidadException;
import co.edu.icesi.miniproyecto.exceptions.BusIDRepetidoException;
import co.edu.icesi.miniproyecto.exceptions.BusNullException;
import co.edu.icesi.miniproyecto.exceptions.BusPlacaRepetidaException;
import co.edu.icesi.miniproyecto.exceptions.BusTipoException;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;

public interface IBusService {
	
	public Tmio1Bus agregarBus(Tmio1Bus bus) throws BusNullException, BusTipoException, BusCapacidadException, BusPlacaRepetidaException, BusIDRepetidoException ;
	
	public Tmio1Bus eliminarBus(Integer id);
	
	public Tmio1Bus consultarBus(Integer id);
	
	public Tmio1Bus actualizarBus(Integer id, BigDecimal capacidad, String marca, BigDecimal modelo, String placa,
			TipoBus tipo);
	
	public Iterable<Tmio1Bus> findAllBuses();

}
