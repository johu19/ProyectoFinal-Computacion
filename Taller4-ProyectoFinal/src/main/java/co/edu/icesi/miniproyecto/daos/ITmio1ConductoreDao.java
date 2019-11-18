package co.edu.icesi.miniproyecto.daos;

import java.util.Date;
import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

public interface ITmio1ConductoreDao {
	
	void save(Tmio1Conductore conductor);
	void delete(Tmio1Conductore conductor);
	void update(Tmio1Conductore conductor);
	Tmio1Conductore findByCedula(String cedula);
	List<Tmio1Conductore> findByNombre(String nombre);
	List<Tmio1Conductore> findByApellidos(String apellidos);
	List<Tmio1Conductore> findAll();
	
	
}
