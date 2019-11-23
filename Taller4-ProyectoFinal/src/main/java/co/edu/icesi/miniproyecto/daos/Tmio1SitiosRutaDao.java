package co.edu.icesi.miniproyecto.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

@Repository
@Scope("singleton")
public class Tmio1SitiosRutaDao implements ITmio1SitiosRutaDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1SitiosRuta sr) {
		entityManager.persist(sr);
	}

	@Override
	public void delete(Tmio1SitiosRuta sr) {
		entityManager.remove(sr);
	}

	@Override
	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK pk) {
		return entityManager.find(Tmio1SitiosRuta.class, pk);
	}

	@Override
	public void update(Tmio1SitiosRuta sr) {
		entityManager.merge(sr);
	}

	@Override
	public List<Tmio1SitiosRuta> findByPlaneId(String id) {
		String jpql = "SELECT a FROM Tmio1SitiosRuta a WHERE a.planeID='"+id+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1SitiosRuta> findAll() {
		String jpql = "SELECT a FROM Tmio1SitiosRuta a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

}
