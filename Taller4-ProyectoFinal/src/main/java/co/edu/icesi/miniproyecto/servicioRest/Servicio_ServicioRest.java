package co.edu.icesi.miniproyecto.servicioRest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.services.ServicioService;

@RestController
public class Servicio_ServicioRest {
	
	@Autowired
	private ServicioService serv;
	
	@PostMapping("/api/servicios/add")
	public Tmio1Servicio agregarServicio(@RequestBody Tmio1Servicio servicio) {
		try {
			return serv.agregarServicio(servicio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/api/servicios/findAll")
	public Iterable<Tmio1Servicio> findAllServicios(){
		return serv.findAllServicios();
	}
	
	@DeleteMapping("/api/servicios/borrar")
	public void borrarServicio(@RequestBody Tmio1ServicioPK id) {
		serv.eliminarServicio(id);
	}
	
	
	@GetMapping("api/servicios/findByDate")
	public Iterable<Tmio1Servicio> findByDate(@RequestBody Date d){
		return serv.findByDate(d);
	}

}
