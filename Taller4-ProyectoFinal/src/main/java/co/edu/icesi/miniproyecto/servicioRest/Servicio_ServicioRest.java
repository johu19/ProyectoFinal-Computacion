package co.edu.icesi.miniproyecto.servicioRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.services.ServicioService;

@RestController
public class Servicio_ServicioRest {
	
	@Autowired
	private ServicioService serv;
	
	@PostMapping("/api/servicios/add")
	public void agregarServicio(Tmio1Servicio servicio) {
		try {
			serv.agregarServicio(servicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/api/servicios/findAll")
	public Iterable<Tmio1Servicio> findAllServicios(){
		return serv.findAllServicios();
	}
	
	@DeleteMapping("/api/servicios/borrar")
	public void borrarServicio(Tmio1ServicioPK id) {
		serv.eliminarServicio(id);
	}

}
