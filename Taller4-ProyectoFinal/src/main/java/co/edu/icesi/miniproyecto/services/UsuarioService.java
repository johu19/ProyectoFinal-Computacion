package co.edu.icesi.miniproyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Usuario;
import co.edu.icesi.miniproyecto.repositories.UsuariosRepository;

@Service
public class UsuarioService implements IUsuariosService {
	
	private UsuariosRepository repos;
	
	@Autowired
	public UsuarioService(UsuariosRepository u) {
		repos = u;
	}

	@Override
	public Usuario agregarUsuario(Usuario u) {
		return repos.save(u);
	}

	@Override
	public Usuario consultarUsuario(String id) {
		return repos.findById(id).get();
	}

}
