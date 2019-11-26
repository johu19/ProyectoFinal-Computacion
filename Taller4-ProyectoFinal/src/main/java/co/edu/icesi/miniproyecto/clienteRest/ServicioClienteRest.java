package co.edu.icesi.miniproyecto.clienteRest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

@Component
public class ServicioClienteRest {

	public final static String REST_URI = "http://localhost:8080/";

	private RestTemplate restTemplate = new RestTemplate();
	
	public Tmio1Servicio agregarServicio(Tmio1Servicio servicio) {
		return restTemplate.postForEntity(REST_URI+"api/servicios/add",servicio, Tmio1Servicio.class).getBody();
	}
	
	public Iterable<Tmio1Servicio> findAllServicios(){
		Tmio1Servicio[] servs = restTemplate.getForObject(REST_URI+"api/servicios/findAll", Tmio1Servicio[].class);
		List<Tmio1Servicio> servsL;
		try {
			servsL = Arrays.asList(servs);
			return servsL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void borrarServicio(Tmio1ServicioPK pk) {
		restTemplate.delete(REST_URI+"api/servicios/borrar", pk);
		
	}
	
	
	public Iterable<Tmio1Servicio> findByDate(Date d){
		Tmio1Servicio[] servs = restTemplate.getForObject(REST_URI+"api/servicios/findByDate/"+d.toString(),Tmio1Servicio[].class);
		List<Tmio1Servicio> servsL;
		try {
			servsL = Arrays.asList(servs);
			return servsL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Tmio1Servicio actualizarServicio(Tmio1Servicio servicio) {
		return restTemplate.patchForObject(REST_URI+"api/servicios/update", servicio, Tmio1Servicio.class);
	}

	public Tmio1Servicio findByPlaneID(String planeID) {
		
		try {
			Tmio1Servicio serv = restTemplate.getForObject(REST_URI+"api/servicios/findByPlaneID/"+planeID,Tmio1Servicio.class);
			return serv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}
