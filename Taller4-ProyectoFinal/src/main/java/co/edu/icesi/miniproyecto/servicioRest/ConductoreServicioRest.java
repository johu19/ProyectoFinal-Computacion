package co.edu.icesi.miniproyecto.servicioRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.services.ConductorService;

@Component
public class ConductoreServicioRest {
	
	@Autowired
	private ConductorService serv;
	
	
	public Iterable<Tmio1Conductore> findAllConductores(){
		return serv.findAllConductores();
	}
	
	public void agregarConductor(Tmio1Conductore cond) {
		try {
			serv.agregarConductor(cond);
		} catch (Exception e) {
			
		}
		
	}
	
	

}
