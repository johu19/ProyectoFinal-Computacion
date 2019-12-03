package co.edu.icesi.miniproyecto.clienteRest;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.model.Usuario;

@Component
public class UsuarioClienteRest {
	
	public final static String REST_URI = "http://localhost:8080/";

	private RestTemplate restTemplate = new RestTemplate();
	
	public Usuario agregarUsuario(Usuario usuario) {
		return restTemplate.postForEntity(REST_URI+"api/usuarios/add",usuario, Usuario.class).getBody();
	}
	
	public Usuario findById(String id) {
		Usuario usuario = restTemplate.getForObject(REST_URI+"api/usuarios/findById/"+id, Usuario.class);
		return usuario;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
