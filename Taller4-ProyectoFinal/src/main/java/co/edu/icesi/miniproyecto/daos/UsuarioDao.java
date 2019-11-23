package co.edu.icesi.miniproyecto.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Usuario;

@Repository
@Scope("singleton")
public class UsuarioDao implements IUsuarioDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Usuario u) {
		entityManager.persist(u);
	}

	@Override
	public Usuario findById(String username) {
		return entityManager.find(Usuario.class, username);
	}

}
