package co.edu.icesi.miniproyecto.clienteRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.edu.icesi.miniproyecto.servicioRest.ConductoreServicioRest;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

@Component
public class ConductoreClienteRest {
	
	@Autowired
	private ConductoreServicioRest servRest;
	
	public Iterable<Tmio1Conductore> findAllConductores(){
		return servRest.findAllConductores();
	}
	
	public void agregarConductor(Tmio1Conductore cond) {
		servRest.agregarConductor(cond);
	}

}
