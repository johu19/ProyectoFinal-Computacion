package co.edu.icesi.miniproyecto.services;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.icesi.miniproyecto.daos.Tmio1SitioDao;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

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
	public Tmio1Sitio actualizarSitio(Integer id, String nombre, String desc) {
		Tmio1Sitio si = consultarSitio(id);
		repos.update(si);
		return repos.findById(si.getId());
	}

	@Override
	public Iterable<Tmio1Sitio> findAllSitios() {
		return repos.findAll();
	}
	
	

}
