package co.edu.icesi.miniproyecto.services;

import java.util.Optional;

import co.edu.icesi.miniproyecto.model.Usuario;

public interface IUsuariosService {
	
	public Usuario agregarUsuario(Usuario u);
	
	public Usuario consultarUsuario(String id);

}
