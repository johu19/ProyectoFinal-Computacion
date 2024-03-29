package co.edu.icesi.miniproyecto.servicioRest;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Iterable<Tmio1Servicio> findAllServicios() {
		return serv.findAllServicios();
	}

	@DeleteMapping("/api/servicios/borrar/{id}")
	public void borrarServicio(@PathVariable String id) {
		serv.eliminarServicio(serv.findByPlaneID(id).getId());
	}

	@GetMapping("/api/servicios/findByDate/{d}")
	public Iterable<Tmio1Servicio> findByDate(@PathVariable String d) {
		String[] datos = d.split("_");
		Date date = new Date(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]),Integer.parseInt(datos[2]));
		return serv.findByDate(date);
	}

	@PatchMapping("/api/servicios/update")
	public Tmio1Servicio actualizarServicio(@RequestBody Tmio1Servicio servicio) {

		try {
			return serv.actualizarServicio(servicio);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@GetMapping("api/servicios/findByPlaneID/{planeID}")
	public Tmio1Servicio findByPlaneID(@PathVariable String planeID) {
		return serv.findByPlaneID(planeID);
	}

}
