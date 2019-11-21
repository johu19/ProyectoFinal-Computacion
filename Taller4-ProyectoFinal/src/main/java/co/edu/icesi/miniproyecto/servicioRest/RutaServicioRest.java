package co.edu.icesi.miniproyecto.servicioRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.services.RutaService;

@RestController
public class RutaServicioRest {
	
	@Autowired
	private RutaService serv;
	
	@GetMapping("/api/rutas/findAll")
	public Iterable<Tmio1Ruta> findAllRutas(){
		return serv.findAllRutas();
	}
	
	@PostMapping("/api/rutas/add")
	public Tmio1Ruta agregarRuta(@RequestBody Tmio1Ruta ruta) {
		try {
			return serv.agregarRuta(ruta);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
