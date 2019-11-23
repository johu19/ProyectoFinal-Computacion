package co.edu.icesi.miniproyecto.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

@Repository
@Scope("singleton")
public class Tmio1SitioDao implements ITmio1SitioDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Sitio sitio) {
		entityManager.persist(sitio);
		
	}

	@Override
	public void delete(Tmio1Sitio sitio) {
		entityManager.remove(sitio);
		
	}

	@Override
	public Tmio1Sitio findById(Integer id) {
		return entityManager.find(Tmio1Sitio.class, id);
	}

	@Override
	public void update(Tmio1Sitio sitio) {
		entityManager.merge(sitio);
		
	}

	@Override
	public List<Tmio1Sitio> findAll() {
		String jpql = "SELECT a FROM Tmio1Sitio a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Sitio> findByPlaneId(String planeId) {
		String jpql = "SELECT a FROM Tmio1SitiosRuta a WHERE a.planeID='"+planeId+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}
	
	
	
	

}
