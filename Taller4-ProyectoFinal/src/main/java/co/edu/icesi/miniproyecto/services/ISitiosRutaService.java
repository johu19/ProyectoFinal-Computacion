package co.edu.icesi.miniproyecto.services;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

public interface ISitiosRutaService {
	
	public Tmio1SitiosRuta agregarSitiosRuta(Tmio1SitiosRuta sr);
	
	public Tmio1SitiosRuta eliminarSitiosRuta(Tmio1SitiosRutaPK id);
	
	public Tmio1SitiosRuta consultarSitiosRuta(Tmio1SitiosRutaPK id);
	
	public Tmio1SitiosRuta actualizarSitiosRuta(Tmio1SitiosRuta sr);

}
