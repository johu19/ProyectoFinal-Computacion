package co.edu.icesi.miniproyecto.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.daos.UsuarioDao;
import co.edu.icesi.miniproyecto.model.Usuario;
import co.edu.icesi.miniproyecto.repositories.UsuariosRepository;

@Service
public class UsuarioService implements IUsuariosService {
	
	@Autowired
	private UsuarioDao repos;
	
	

	@Override
	@Transactional
	public Usuario agregarUsuario(Usuario u) {
		repos.save(u);
		return u;
	}

	@Override
	public Usuario consultarUsuario(String id) {
		return repos.findById(id);
	}

}
