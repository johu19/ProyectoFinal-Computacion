package co.edu.icesi.miniproyecto.servicioRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;
import co.edu.icesi.miniproyecto.services.SitiosRutaService;

@RestController
public class SitiosRutaServicioRest {
	
	@Autowired
	private SitiosRutaService serv;
	
	
	@PostMapping("/api/sr/add")
	public Tmio1SitiosRuta agregarSitiosRuta(@RequestBody Tmio1SitiosRuta sr) {
		try {
			return serv.agregarSitiosRuta(sr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@GetMapping("/api/sr/findAll")
	public Iterable<Tmio1SitiosRuta> findAllSitiosRuta(){
		return serv.findAllSitiosRuta();
	}
	
	@DeleteMapping("/api/sr/borrar/{id}")
	public void borrarSitiosRuta(@PathVariable String id) {
		serv.eliminarSitiosRuta(serv.findByPlanedId(id).getId());
	}
	
	@PatchMapping("api/sr/update")
	public Tmio1SitiosRuta actualizarSitiosRuta(@RequestBody Tmio1SitiosRuta sr) {
		try {
			return serv.actualizarSitiosRuta(sr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("api/sr/findByPlaneID/{planeID}")
	public Tmio1SitiosRuta findByPlaneID(@PathVariable String planeID) {
		return serv.findByPlanedId(planeID);
	}

}
