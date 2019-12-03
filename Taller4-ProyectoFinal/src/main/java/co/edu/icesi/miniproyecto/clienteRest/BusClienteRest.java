package co.edu.icesi.miniproyecto.clienteRest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;

@Component
public class BusClienteRest {

	public final static String REST_URI = "http://localhost:8080/";

	private RestTemplate restTemplate;
	
	public BusClienteRest() {
		restTemplate = new RestTemplate();
	}

//	public void agregarBus(Tmio1Bus bus) {
//
//		TransactionBody<Tmio1Bus> transaction = new TransactionBody<Tmio1Bus>("bus", bus);
//		HttpEntity<TransactionBody<Tmio1Bus>> request = new HttpEntity<>(transaction);
//		ResponseEntity<TransactionBody<Tmio1Bus>> response = null;
//
//		try {
//			response = restTemplate.exchange(REST_URI + "api/buses/add", HttpMethod.POST, request,
//					new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
//					});
//		} catch (HttpStatusCodeException e) {
//			int statusCode = e.getStatusCode().value();
//			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//		}
////		response.getBody();
//
//	}

//	public TipoBus[] obtenerTipos() {
//	ResponseEntity<TransactionBody<TipoBus[]>> response = null;
//	HttpEntity request = new HttpEntity(null);
//	try {
//		response = restTemplate.exchange(REST_URI + "api/buses/findTipos", HttpMethod.GET, request,
//				new ParameterizedTypeReference<TransactionBody<TipoBus[]>>() {
//				});
//	} catch (HttpStatusCodeException e) {
//		int statusCode = e.getStatusCode().value();
//		System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//	}
//	if (response != null) {
//		TipoBus[] allTipos = response.getBody().getBody();
//		return allTipos;
//	}
//
//	return null;
//}
	
	
//	public Iterable<Tmio1Bus> findAllBuses() {
//	TransactionBody<List<Tmio1Bus>> transactionBody = new TransactionBody<>("buses", new ArrayList<Tmio1Bus>());
//	HttpEntity<TransactionBody<List<Tmio1Bus>>> request = new HttpEntity<>(transactionBody);
//	ResponseEntity<TransactionBody<List<Tmio1Bus>>> response = null;
//
//	try {
//		response = restTemplate.exchange(REST_URI + "api/buses/findAll", HttpMethod.GET, request,
//				new ParameterizedTypeReference<TransactionBody<List<Tmio1Bus>>>() {
//				});
//	} catch (HttpStatusCodeException e) {
//		int statusCode = e.getStatusCode().value();
//		System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
//	}
//	if (response != null) {
//		Iterable<Tmio1Bus> allBuses = response.getBody().getBody();
//		return allBuses;
//	}
//
//	return null;
//
//}
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Tmio1Bus agregarBus(Tmio1Bus bus) {
		return restTemplate.postForEntity(REST_URI+"api/buses/add", bus, Tmio1Bus.class).getBody();
	}
	
	public Iterable<Tmio1Bus> findAllBuses(){
		Tmio1Bus[] buses = restTemplate.getForObject(REST_URI + "api/buses/findAll", Tmio1Bus[].class);
		List<Tmio1Bus> listB;
		try {
			listB = Arrays.asList(buses);
			return listB;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public TipoBus[] obtenerTipos() {
		TipoBus[] tipos = restTemplate.getForObject(REST_URI+"api/buses/findTipos", TipoBus[].class);
		return tipos;
	}

}
