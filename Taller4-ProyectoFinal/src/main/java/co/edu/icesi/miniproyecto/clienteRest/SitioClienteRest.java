package co.edu.icesi.miniproyecto.clienteRest;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

@Component
public class SitioClienteRest {
	
	public final static String REST_URI = "http://localhost:8080/";

	private RestTemplate restTemplate;
	
	public SitioClienteRest() {
		restTemplate= new RestTemplate();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(1000);
		requestFactory.setReadTimeout(1000);

		restTemplate.setRequestFactory(requestFactory);
	}
	
	public Tmio1Sitio agregarSitio(Tmio1Sitio sitio) {
		return restTemplate.postForEntity(REST_URI+"api/sitios/add",sitio, Tmio1Sitio.class).getBody();
	}
	
	public Iterable<Tmio1Sitio> findAllSitios(){
		Tmio1Sitio[] sitios = restTemplate.getForObject(REST_URI+"api/sitios/findAll", Tmio1Sitio[].class);
		
		List<Tmio1Sitio> sitiosL;
		try {
			sitiosL = Arrays.asList(sitios);
			return sitiosL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void borrarSitio(Integer id) {
		restTemplate.delete(REST_URI+"api/sitios/borrar/"+id);
		
	}
	
	public Tmio1Sitio actualizarSitio(Tmio1Sitio sitio) {
		
		return restTemplate.patchForObject(REST_URI+"api/sitios/update", sitio, Tmio1Sitio.class);
	}
	
	public Tmio1Sitio findById(Integer id){
		Tmio1Sitio sitio = restTemplate.getForObject(REST_URI+"api/sitios/findById/"+id, Tmio1Sitio.class);
		return sitio;
		
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	

}
