package co.edu.icesi.miniproyecto.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.clienteRest.UsuarioClienteRest;
import co.edu.icesi.miniproyecto.model.Usuario;
import co.edu.icesi.miniproyecto.repositories.UsuariosRepository;
import co.edu.icesi.miniproyecto.services.UsuarioService;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UsuarioClienteRest userRest;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		if (userRest.findById(username) != null) {
			Usuario usuario = userRest.findById(username);
			User.UserBuilder builder = User.withUsername(username).password(usuario.getPassword())
					.roles(usuario.getTipo().toString());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");

		}
	}
}