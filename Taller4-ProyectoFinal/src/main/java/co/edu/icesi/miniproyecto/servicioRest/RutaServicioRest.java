package co.edu.icesi.miniproyecto.servicioRest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.services.RutaService;

@Component
public class RutaServicioRest {
	
	private RutaService serv;
	
	@GetMapping("/api/rutas/findAll")
	public Iterable<Tmio1Ruta> findAllRutas(){
		return serv.findAllRutas();
	}
	
	@PostMapping("/api/rutas/add")
	public void agregarRuta(Tmio1Ruta ruta) {
		try {
			serv.agregarRuta(ruta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
