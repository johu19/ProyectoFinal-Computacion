package co.edu.icesi.miniproyecto.daos;

import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

public interface ITmio1SitiosRutaDao {
	
	void save(Tmio1SitiosRuta sr);
	void delete(Tmio1SitiosRuta sr);
	Tmio1SitiosRuta findById(Tmio1SitiosRutaPK pk);
	void update(Tmio1SitiosRuta sr);
	List<Tmio1SitiosRuta> findByPlaneId(String id);
	List<Tmio1SitiosRuta> findAll();
	

}
