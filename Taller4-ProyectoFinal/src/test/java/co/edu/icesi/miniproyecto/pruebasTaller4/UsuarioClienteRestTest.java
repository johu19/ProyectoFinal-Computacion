package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.mockito.Mock;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.UsuarioClienteRest;
import co.edu.icesi.miniproyecto.model.TipoUsuario;
import co.edu.icesi.miniproyecto.model.Usuario;
import co.edu.icesi.miniproyecto.servicioRest.UsuarioServicioRest;

public class UsuarioClienteRestTest {

	private UsuarioClienteRest delegado;
	
	@Mock
	private UsuarioServicioRest mock;
	
	@Test
	public void testagregarUsuario () {
		Usuario usuario = new Usuario();
		usuario.setPassword("123456789");
		usuario.setTipo(TipoUsuario.Administrador);
		usuario.setType(TipoUsuario.Administrador);
		usuario.setUsername("pepe");
		
		when(mock.agregarUsuario(usuario)).thenReturn(usuario);
	}
	
	@Test
	public void testfindById () {

	}
}
