package co.edu.icesi.miniproyecto.clienteRest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

public class RutaClienteRest {
	
public final static String REST_URI = "http://localhost:8080/";
	
	private RestTemplate rest = new RestTemplate();
	
	public Iterable<Tmio1Ruta> findAllRutas(){
		ResponseEntity<TransactionBody<Iterable<Tmio1Ruta>>> response = null;
		HttpEntity request = new HttpEntity(null);
		try {
			response = rest.exchange(REST_URI+"/api/rutas/findAll", HttpMethod.GET,request,
					new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Ruta>>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		if(response !=null) {
			Iterable<Tmio1Ruta> allRutas = response.getBody().getBody();
			return allRutas;
		}
		
		return null;
	}
	
	public void agregarRuta(Tmio1Ruta ruta) {
		TransactionBody<Tmio1Ruta> transaction = new TransactionBody<Tmio1Ruta>("ruta", ruta);
		HttpEntity<TransactionBody<Tmio1Ruta>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = null;

		try {
			response = rest.exchange(REST_URI + "api/rutas/add", HttpMethod.POST, request,
					new ParameterizedTypeReference<TransactionBody<Tmio1Ruta>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		response.getBody();
	}

}
