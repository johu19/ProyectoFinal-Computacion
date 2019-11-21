package co.edu.icesi.miniproyecto.services;

import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

public interface ISitioService {
	
	public Tmio1Sitio agregarSitio(Tmio1Sitio sitio);
	
	public Tmio1Sitio eliminarSitio(Integer id);
	
	public Tmio1Sitio consultarSitio(Integer id);
	
	public Tmio1Sitio actualizarSitio(Integer id, String nombre, String desc) ;
	
	
	public Iterable<Tmio1Sitio> findAllSitios();

}
