package co.edu.icesi.miniproyecto.servicioRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.services.SitioService;

@RestController
public class SitioServicioRest {
	
	@Autowired
	private SitioService serv;
	
	@PostMapping("/api/sitios/add")
	public Tmio1Sitio agregarSitio(@RequestBody Tmio1Sitio sitio) {
		return serv.agregarSitio(sitio);
	}
	
	
	@GetMapping("/api/sitios/findAll")
	public Iterable<Tmio1Sitio> findAllSitios(){
		return serv.findAllSitios();
	}
	
	@DeleteMapping("/api/sitios/borrar")
	public void borrarSitio(@RequestBody Integer id) {
		serv.eliminarSitio(id);
	}
	
	
//	@GetMapping("/api/sitios/findById")
//	public Tmio1Sitio consultarSitio(@RequestBody Integer id) {
//		return serv.consultarSitio(id);
//	}

}
