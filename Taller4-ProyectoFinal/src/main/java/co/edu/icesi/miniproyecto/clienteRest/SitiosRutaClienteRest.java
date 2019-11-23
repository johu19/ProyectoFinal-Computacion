package co.edu.icesi.miniproyecto.clienteRest;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

@Component
public class SitiosRutaClienteRest {
	
	public final static String REST_URI = "http://localhost:8080/";

	private RestTemplate restTemplate = new RestTemplate();
	
	
	public Tmio1SitiosRuta agregarSitiosRuta(Tmio1SitiosRuta sr) {
		return restTemplate.postForEntity(REST_URI+"api/sr/add", sr, Tmio1SitiosRuta.class).getBody();
	}
	
	
	public Iterable<Tmio1SitiosRuta> findAllSitiosRuta(){
		Tmio1SitiosRuta[] srs = restTemplate.getForObject(REST_URI+"api/sr/findAll", Tmio1SitiosRuta[].class);
		List<Tmio1SitiosRuta> srsL;
		try {
			srsL = Arrays.asList(srs);
			return srsL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void borrarSitiosRuta(Tmio1SitiosRutaPK pk) {
		restTemplate.delete(REST_URI+"api/sr/borrar",pk);
	}
	
	
	public Tmio1SitiosRuta actualizarSitiosRuta(Tmio1SitiosRuta sr) {
		return  restTemplate.patchForObject(REST_URI+"api/sr/update", sr,Tmio1SitiosRuta.class);
	}

}
