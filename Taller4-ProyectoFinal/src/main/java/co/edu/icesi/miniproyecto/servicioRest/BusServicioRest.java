package co.edu.icesi.miniproyecto.servicioRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.clienteRest.TransactionBody;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.services.BusService;

@RestController
public class BusServicioRest {

	@Autowired
	private BusService serv;

	@PostMapping("/api/buses/add")
	public Tmio1Bus agregarBus(@RequestBody Tmio1Bus b) {
		try {
			return serv.agregarBus(b);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
//	@RequestMapping(value = "/api/buses/add", method = RequestMethod.POST)
//	public Tmio1Bus agregarBus(@RequestBody Tmio1Bus b) {
//		try {
//			return serv.agregarBus(b);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}	
//	}
	
	@GetMapping("/api/buses/findAll")
	public Iterable<Tmio1Bus> findAll(){
		return serv.findAllBuses();
	}
	
	
	@GetMapping("/api/buses/findTipos")
	public TipoBus[] obtenerTipos() {
		
		return serv.obtenerTipos();
	}

}
