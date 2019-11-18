package co.edu.icesi.miniproyecto.servicioRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.services.BusService;

@RestController
public class BusServicioRest {

	@Autowired
	private BusService serv;

	@PostMapping("/api/buses/add")
	public void agregarBus(Tmio1Bus bus) {
		try {
			serv.agregarBus(bus);
		} catch (Exception e) {
			
		}
		
	}

	@GetMapping("api/buses/findAll")
	public Iterable<Tmio1Bus> findAllBuses() {
		return serv.findAllBuses();
	}
	
	@GetMapping("api/findTipos")
	public TipoBus[] obtenerTipos() {
		return serv.obtenerTipos();
	}

}
