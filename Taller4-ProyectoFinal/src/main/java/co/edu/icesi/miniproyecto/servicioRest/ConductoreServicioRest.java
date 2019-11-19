package co.edu.icesi.miniproyecto.servicioRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.services.ConductorService;

@RestController
public class ConductoreServicioRest {
	
	@Autowired
	private ConductorService serv;
	
	
	@GetMapping("/api/conductore/findAll")
	public Iterable<Tmio1Conductore> findAllConductores(){
		return serv.findAllConductores();
	}
	
	
	@PostMapping("/api/conductore/add")
	public Tmio1Conductore agregarConductor(@RequestBody Tmio1Conductore cond) {
		try {
			return serv.agregarConductor(cond);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	

}
