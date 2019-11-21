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

//	public void agregarServicio(Tmio1Servicio servicio) {
//
//		TransactionBody<Tmio1Servicio> transaction = new TransactionBody<Tmio1Servicio>("servicio", servicio);
//		HttpEntity<TransactionBody<Tmio1Servicio>> request = new HttpEntity<>(transaction);
//		ResponseEntity<TransactionBody<Tmio1Servicio>> response = null;
//
//		try {
//			response = rest.exchange(REST_URI + "api/servicios/add", HttpMethod.POST, request,
//					new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
//					});
//		} catch (HttpStatusCodeException e) {
//			int statusCode = e.getStatusCode().value();
//			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//		}
//		response.getBody();
//
//	}
//
//	public Iterable<Tmio1Servicio> findAllServicios() {
//		ResponseEntity<TransactionBody<Iterable<Tmio1Servicio>>> response = null;
//		HttpEntity request = new HttpEntity(null);
//		try {
//			response = rest.exchange(REST_URI + "/api/servicios/findAll", HttpMethod.GET, request,
//					new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Servicio>>>() {
//					});
//		} catch (HttpStatusCodeException e) {
//			int statusCode = e.getStatusCode().value();
//			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//		}
//		if (response != null) {
//			Iterable<Tmio1Servicio> allServicios = response.getBody().getBody();
//			return allServicios;
//		}
//
//		return null;
//
//	}
//
//	public void borrarServicio(Tmio1ServicioPK id) {
//
//		TransactionBody<Tmio1ServicioPK> transaction = new TransactionBody<>("id", id);
//		HttpEntity<TransactionBody<Tmio1ServicioPK>> request = new HttpEntity<>(transaction);
//		ResponseEntity<TransactionBody<Tmio1ServicioPK>> response = null;
//
//		try {
//
//			response = rest.exchange(REST_URI + "/api/servicios/borrar", HttpMethod.DELETE, request,
//					new ParameterizedTypeReference<TransactionBody<Tmio1ServicioPK>>() {
//					});
//
//		} catch (HttpStatusCodeException e) {
//			int statusCode = e.getStatusCode().value();
//			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//		}
//		response.getBody();
//
//	}
	
	
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
		restTemplate.delete(REST_URI+"api/servicios/borrar/", pk);
		
	}
	
	
	public Iterable<Tmio1Servicio> findByDate(Date d){
		Tmio1Servicio[] servs = restTemplate.getForObject(REST_URI+"api/servicios/findByDate",Tmio1Servicio[].class);
		List<Tmio1Servicio> servsL;
		try {
			servsL = Arrays.asList(servs);
			return servsL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
