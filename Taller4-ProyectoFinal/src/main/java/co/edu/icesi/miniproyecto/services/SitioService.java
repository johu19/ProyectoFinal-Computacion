package co.edu.icesi.miniproyecto.services;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.icesi.miniproyecto.daos.Tmio1SitioDao;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;

@Service
public class SitioService implements ISitioService{
	
	@Autowired
	private Tmio1SitioDao repos;

	@Override
	@Transactional
	public Tmio1Sitio agregarSitio(Tmio1Sitio sitio) {
		repos.save(sitio);
		return sitio;
	}

	@Override
	@Transactional
	public Tmio1Sitio eliminarSitio(Integer id) {
		Tmio1Sitio si = consultarSitio(id); 
		repos.delete(si);
		return si;
	}

	@Override
	public Tmio1Sitio consultarSitio(Integer id) {
		return repos.findById(id);
	}

	@Override
	@Transactional
	public Tmio1Sitio actualizarSitio(Tmio1Sitio sitio) {
		
		Tmio1Sitio si = consultarSitio(sitio.getId());
		repos.delete(si);
		Tmio1Sitio newSi = new Tmio1Sitio();
		newSi.setNombre(sitio.getNombre());
		newSi.setDescripcion(sitio.getDescripcion());
		repos.save(newSi);
		return newSi;
	}

	@Override
	public Iterable<Tmio1Sitio> findAllSitios() {
		return repos.findAll();
	}
	
	
	
	

}
