package co.edu.icesi.miniproyecto.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.daos.Tmio1SitiosRutaDao;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

@Service
public class SitiosRutaService implements ISitiosRutaService {
	
	@Autowired
	private Tmio1SitiosRutaDao repos;

	@Override
	@Transactional
	public Tmio1SitiosRuta agregarSitiosRuta(Tmio1SitiosRuta sr) {
		repos.save(sr);
		return sr;
	}

	@Override
	@Transactional
	public Tmio1SitiosRuta eliminarSitiosRuta(Tmio1SitiosRutaPK id) {
		Tmio1SitiosRuta sr = repos.findById(id);
		repos.delete(sr);
		return sr;
	}

	@Override
	public Tmio1SitiosRuta consultarSitiosRuta(Tmio1SitiosRutaPK id) {
		return repos.findById(id);
	}


	public Tmio1SitiosRuta findByPlanedId(String planeId) {
		return repos.findByPlaneId(planeId).get(0);
	}
	
	public Iterable<Tmio1SitiosRuta> findAllSitiosRuta(){
		return repos.findAll();
	}

	@Override
	public Tmio1SitiosRuta actualizarSitiosRuta(Tmio1SitiosRuta sr) {
		Tmio1SitiosRuta old = repos.findByPlaneId(sr.getPlaneID()).get(0);
		repos.delete(old);
		Tmio1SitiosRutaPK newPK = new Tmio1SitiosRutaPK();
		newPK.setIdRuta(sr.getTmio1Ruta1().getId());
		newPK.setIdSitio(sr.getTmio1Sitio1().getId());
		sr.setPlaneID(newPK.getIdRuta()+"_"+newPK.getIdSitio());
		sr.setId(newPK);
		repos.save(sr);
		return sr;
	}

}
