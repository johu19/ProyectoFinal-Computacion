package co.edu.icesi.miniproyecto.clienteRest;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

@Component
public class RutaClienteRest {
	
public final static String REST_URI = "http://localhost:8080/";
	
	private RestTemplate restTemplate = new RestTemplate();
	
//	public Iterable<Tmio1Ruta> findAllRutas(){
//		ResponseEntity<TransactionBody<Iterable<Tmio1Ruta>>> response = null;
//		HttpEntity request = new HttpEntity(null);
//		try {
//			response = rest.exchange(REST_URI+"/api/rutas/findAll", HttpMethod.GET,request,
//					new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Ruta>>>() {
//					});
//		} catch (HttpStatusCodeException e) {
//			int statusCode = e.getStatusCode().value();
//			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//		}
//		if(response !=null) {
//			Iterable<Tmio1Ruta> allRutas = response.getBody().getBody();
//			return allRutas;
//		}
//		
//		return null;
//	}
//	
//	public void agregarRuta(Tmio1Ruta ruta) {
//		TransactionBody<Tmio1Ruta> transaction = new TransactionBody<Tmio1Ruta>("ruta", ruta);
//		HttpEntity<TransactionBody<Tmio1Ruta>> request = new HttpEntity<>(transaction);
//		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;
//
//		try {
//			response = rest.exchange(REST_URI + "api/rutas/add", HttpMethod.POST, request,
//					new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
//					});
//		} catch (HttpStatusCodeException e) {
//			int statusCode = e.getStatusCode().value();
//			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//		}
//		response.getBody();
//	}

	
	
	public Iterable<Tmio1Ruta> findAllRutas(){
		Tmio1Ruta[] rutas = restTemplate.getForObject(REST_URI+"api/rutas/findAll", Tmio1Ruta[].class);
		List<Tmio1Ruta> rutasL;
		try {
			rutasL = Arrays.asList(rutas);
			return rutasL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Tmio1Ruta agregarRuta(Tmio1Ruta ruta) {
		return restTemplate.postForEntity(REST_URI+"api/rutas/add",ruta, Tmio1Ruta.class).getBody();
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
}
