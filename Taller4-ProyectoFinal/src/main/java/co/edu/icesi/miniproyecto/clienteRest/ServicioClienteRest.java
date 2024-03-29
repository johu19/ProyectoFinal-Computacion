package co.edu.icesi.miniproyecto.clienteRest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

@Component
public class ServicioClienteRest {

	public final static String REST_URI = "http://localhost:8080/";

	private RestTemplate restTemplate;

	public ServicioClienteRest() {
		restTemplate= new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(1000);
		requestFactory.setReadTimeout(1000);

		restTemplate.setRequestFactory(requestFactory);
	}

	public Tmio1Servicio agregarServicio(Tmio1Servicio servicio) {
		return restTemplate.postForEntity(REST_URI + "api/servicios/add", servicio, Tmio1Servicio.class).getBody();
	}

	public Iterable<Tmio1Servicio> findAllServicios() {
		Tmio1Servicio[] servs = restTemplate.getForObject(REST_URI + "api/servicios/findAll", Tmio1Servicio[].class);
		List<Tmio1Servicio> servsL;
		try {
			servsL = Arrays.asList(servs);
			return servsL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void borrarServicio(String planeID) {
		restTemplate.delete(REST_URI + "api/servicios/borrar/"+planeID);

	}

	public Iterable<Tmio1Servicio> findByDate(Date d) {
		String date = d.getYear()+"_"+d.getMonth()+"_"+d.getDate();
		Tmio1Servicio[] servs = restTemplate.getForObject(REST_URI + "api/servicios/findByDate/" + date,
				Tmio1Servicio[].class);
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
		return restTemplate.patchForObject(REST_URI + "api/servicios/update", servicio, Tmio1Servicio.class);
	}

	public Tmio1Servicio findByPlaneID(String planeID) {

		try {
			Tmio1Servicio serv = restTemplate.getForObject(REST_URI + "api/servicios/findByPlaneID/" + planeID,
					Tmio1Servicio.class);
			return serv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
