package co.edu.icesi.miniproyecto.clienteRest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.servicioRest.ConductoreServicioRest;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

@Component
public class ConductoreClienteRest {
	
	public final static String REST_URI = "http://localhost:8080/";
	
	private RestTemplate restTemplate = new RestTemplate();
	
//	public Iterable<Tmio1Conductore> findAllConductores(){
//		ResponseEntity<TransactionBody<Iterable<Tmio1Conductore>>> response = null;
//		HttpEntity request = new HttpEntity(null);
//		try {
//			response = rest.exchange(REST_URI+"/api/conductore/findAll", HttpMethod.GET,request,
//					new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Conductore>>>() {
//					});
//		} catch (HttpStatusCodeException e) {
//			int statusCode = e.getStatusCode().value();
//			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//		}
//		if(response !=null) {
//			Iterable<Tmio1Conductore> allConductores = response.getBody().getBody();
//			return allConductores;
//		}
//		
//		return null;
//	}
//	
//	public void agregarConductor(Tmio1Conductore cond) {
//		TransactionBody<Tmio1Conductore> transaction = new TransactionBody<Tmio1Conductore>("cond", cond);
//		HttpEntity<TransactionBody<Tmio1Conductore>> request = new HttpEntity<>(transaction);
//		ResponseEntity<TransactionBody<Tmio1Conductore>> response = null;
//
//		try {
//			response = rest.exchange(REST_URI + "api/conductore/add", HttpMethod.POST, request,
//					new ParameterizedTypeReference<TransactionBody<Tmio1Conductore>>() {
//					});
//		} catch (HttpStatusCodeException e) {
//			int statusCode = e.getStatusCode().value();
//			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//		}
//		response.getBody();
//	}
	
	
	public Tmio1Conductore agregarConductor(Tmio1Conductore cond) {
		return restTemplate.postForEntity(REST_URI+"api/conductore/add", cond, Tmio1Conductore.class).getBody();
	}
	
	public Iterable<Tmio1Conductore> findAllConductore(){
		Tmio1Conductore[] conds = restTemplate.getForObject(REST_URI+"api/conductore/findAll", Tmio1Conductore[].class);
		List<Tmio1Conductore> condsL;
		try {
			condsL=Arrays.asList(conds);
			return condsL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
