package co.edu.icesi.miniproyecto.clienteRest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

public class ServicioClienteRest {

	public final static String REST_URI = "http://localhost:8080/";

	private RestTemplate rest = new RestTemplate();

	public void agregarServicio(Tmio1Servicio servicio) {

		TransactionBody<Tmio1Servicio> transaction = new TransactionBody<Tmio1Servicio>("servicio", servicio);
		HttpEntity<TransactionBody<Tmio1Servicio>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Servicio>> response = null;

		try {
			response = rest.exchange(REST_URI + "api/servicios/add", HttpMethod.POST, request,
					new ParameterizedTypeReference<TransactionBody<Tmio1Servicio>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		response.getBody();

	}

	public Iterable<Tmio1Servicio> findAllServicios() {
		ResponseEntity<TransactionBody<Iterable<Tmio1Servicio>>> response = null;
		HttpEntity request = new HttpEntity(null);
		try {
			response = rest.exchange(REST_URI + "/api/servicios/findAll", HttpMethod.GET, request,
					new ParameterizedTypeReference<TransactionBody<Iterable<Tmio1Servicio>>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		if (response != null) {
			Iterable<Tmio1Servicio> allServicios = response.getBody().getBody();
			return allServicios;
		}

		return null;

	}

	public void borrarServicio(Tmio1ServicioPK id) {

		TransactionBody<Tmio1ServicioPK> transaction = new TransactionBody<>("id", id);
		HttpEntity<TransactionBody<Tmio1ServicioPK>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1ServicioPK>> response = null;

		try {

			response = rest.exchange(REST_URI + "/api/servicios/borrar", HttpMethod.DELETE, request,
					new ParameterizedTypeReference<TransactionBody<Tmio1ServicioPK>>() {
					});

		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		response.getBody();

	}

}
