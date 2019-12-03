package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.RutaClienteRest;
import co.edu.icesi.miniproyecto.clienteRest.UsuarioClienteRest;
import co.edu.icesi.miniproyecto.model.TipoUsuario;
import co.edu.icesi.miniproyecto.model.Usuario;
import co.edu.icesi.miniproyecto.servicioRest.UsuarioServicioRest;

public class UsuarioClienteRestTest {

	public final static String REST_URI = "http://localhost:8080/";
	
	private UsuarioClienteRest delegado;
	
	@Mock
	private RestTemplate restTemplate;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		delegado = new UsuarioClienteRest();
		restTemplate = new RestTemplate();
		delegado.setRestTemplate(restTemplate);
	}
	
	@Test
	public void testagregarUsuario () {
		Usuario usuario = new Usuario();
		usuario.setPassword("123456789");
		usuario.setTipo(TipoUsuario.Administrador);
		usuario.setType(TipoUsuario.Administrador);
		usuario.setUsername("pepe");
		
		assertEquals(delegado.agregarUsuario(usuario),usuario);
		when(restTemplate.postForEntity(REST_URI+"api/usuarios/add",usuario, Usuario.class).getBody()).thenReturn(usuario);
	}
	
	@Test
	public void testfindById () {
		Usuario usuario = new Usuario();
		usuario.setPassword("123456789");
		usuario.setTipo(TipoUsuario.Administrador);
		usuario.setType(TipoUsuario.Administrador);
		usuario.setUsername("pepe");
		
		assertEquals(delegado.findById(usuario.getUsername()),usuario);
		when(restTemplate.getForObject(REST_URI+"api/usuarios/findById/"+usuario.getUsername(), Usuario.class)).thenReturn(usuario);
	}
}
