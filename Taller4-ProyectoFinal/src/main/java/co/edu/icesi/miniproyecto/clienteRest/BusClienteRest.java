package co.edu.icesi.miniproyecto.clienteRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.servicioRest.BusServicioRest;

@Component
public class BusClienteRest {

	public final static String REST_URI = "http://localhost8080/";

	@Autowired
	private BusServicioRest servRest;

	private RestTemplate rest = new RestTemplate();

	public void agregarBus(Tmio1Bus bus) {

		TransactionBody<Tmio1Bus> transaction = new TransactionBody<Tmio1Bus>("bus", bus);
		HttpEntity<TransactionBody<Tmio1Bus>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<Tmio1Bus>> response = null;

		try {
			response = rest.exchange(REST_URI + "api/buses/add", HttpMethod.POST, request,
					new ParameterizedTypeReference<TransactionBody<Tmio1Bus>>() {
					});
		} catch (HttpStatusCodeException e) {
			int statusCode = e.getStatusCode().value();
			System.out.println("ERROR: " + statusCode + " - " + e.getResponseBodyAsString());
		}
		response.getBody();

	}

	public Iterable<Tmio1Bus> findAllBuses() {
		return servRest.findAllBuses();
	}

	public TipoBus[] obtenerTipos() {
		return servRest.obtenerTipos();
	}

}