package co.edu.icesi.miniproyecto.daos;

import java.util.Date;
import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

public interface ITmio1ServicioDao {
	
	void save(Tmio1Servicio serv);
	void delete(Tmio1Servicio serv);
	Tmio1Servicio findById(Tmio1ServicioPK pk);
	void update(Tmio1Servicio serv);
	void clearEM();
	List<Tmio1Servicio> findByPlaneId(String id);
	List<Tmio1Servicio> findAll();
	List<Object[]> findConductoresWithAmountOfServicios(Date fechaLimite);
	List<Tmio1Ruta> findRutasWithLessThan10(Date date);
	List<Tmio1Bus> findBusesWithMoreThanOneServicio(Date date);
	
	

}
