package co.edu.icesi.miniproyecto.daos;

import co.edu.icesi.miniproyecto.model.Usuario;

public interface IUsuarioDao {
	
	void save(Usuario u);
	Usuario findById(String username);

}
