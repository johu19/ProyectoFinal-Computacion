package co.edu.icesi.miniproyecto.daos;

import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

public interface ITmio1SitioDao {
	
	void save(Tmio1Sitio sitio);
	void delete(Tmio1Sitio sitio);
	Tmio1Sitio findById(Integer id);
	void update(Tmio1Sitio sitio);
	List<Tmio1Sitio> findAll();
	

}
