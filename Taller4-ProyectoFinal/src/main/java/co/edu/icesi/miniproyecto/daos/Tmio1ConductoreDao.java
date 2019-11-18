package co.edu.icesi.miniproyecto.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

@Repository
@Scope("singleton")
public class Tmio1ConductoreDao implements ITmio1ConductoreDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1Conductore conductor) {
		entityManager.persist(conductor);
	}

	@Override
	public void delete(Tmio1Conductore conductor) {
		entityManager.remove(conductor);
	}

	@Override
	public void update(Tmio1Conductore conductor) {
		entityManager.merge(conductor);
	}

	@Override
	public Tmio1Conductore findByCedula(String cedula) {
		return entityManager.find(Tmio1Conductore.class, cedula);
	}

	@Override
	public List<Tmio1Conductore> findByNombre(String nombre) {
		String jpql = "SELECT a FROM Tmio1Conductore a WHERE a.nombre='"+nombre+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByApellidos(String apellidos) {
		String jpql = "SELECT a FROM Tmio1Conductore a WHERE a.apellidos='"+apellidos+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findAll() {
		String jpql = "SELECT a FROM Tmio1Conductore a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

//	@Override
//	public List<Tmio1Conductore> findByServiceDate(Date date) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	

}
