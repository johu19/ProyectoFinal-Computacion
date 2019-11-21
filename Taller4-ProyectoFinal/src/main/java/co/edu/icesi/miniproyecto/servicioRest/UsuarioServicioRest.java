package co.edu.icesi.miniproyecto.servicioRest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.miniproyecto.model.Usuario;
import co.edu.icesi.miniproyecto.services.UsuarioService;

@RestController
public class UsuarioServicioRest {
	
	@Autowired
	private UsuarioService serv;
	
	@PostMapping("/api/usuarios/add")
	public Usuario agregarUsuario(@RequestBody Usuario usuario) {
		return serv.agregarUsuario(usuario);
	}
	
	@GetMapping("/api/usuarios/findById")
	public Usuario findById(@PathVariable String id) {
		return serv.consultarUsuario(id);
	}

}
