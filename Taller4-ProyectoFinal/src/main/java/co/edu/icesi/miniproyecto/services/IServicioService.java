package co.edu.icesi.miniproyecto.services;

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

public interface IServicioService {
	
public Tmio1Servicio agregarServicio(Tmio1Servicio servicio) throws BusNullException, ConductorNullException, RutaNullException, BusNoRegistradoException, RutaNoRegistradaException, ConductorNoRegistradoException, ServicioFechasException;
	
	public Tmio1Servicio eliminarServicio(Tmio1ServicioPK id);
	
	public Tmio1Servicio consultarServicio(Tmio1ServicioPK id);
	
	public Tmio1Servicio actualizarServicio(Tmio1Servicio serv)
			throws BusNullException, ConductorNullException, RutaNullException, BusNoRegistradoException, RutaNoRegistradaException, ConductorNoRegistradoException, ServicioFechasException;

}
