package co.edu.icesi.miniproyecto.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

@Repository
@Scope("singleton")
public class Tmio1BusDao implements ITmio1BusDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Tmio1Bus> findByPlaca(String placa) {
		String jpql = "SELECT a FROM Tmio1Bus a WHERE a.placa = '"+placa+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void save(Tmio1Bus bus) {
		entityManager.persist(bus);
		
	}

	@Override
	public Tmio1Bus findById(Integer id) {
		return entityManager.find(Tmio1Bus.class, id);
	}

	@Override
	public void update(Tmio1Bus bus) {
		entityManager.merge(bus);
		
	}

	@Override
	public void delete(Tmio1Bus bus) {
		entityManager.remove(bus);
		
	}

	@Override
	public List<Tmio1Bus> findByMarca(String marca) {
		String jpql = "SELECT a FROM Tmio1Bus a WHERE a.marca = '"+marca+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByModelo(BigDecimal bigDecimal) {
		String jpql = "SELECT a FROM Tmio1Bus a WHERE a.modelo = '"+bigDecimal+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findAll() {
		String jpql = "SELECT a FROM Tmio1Bus a";
		return 	entityManager.createQuery(jpql).getResultList();
	}


	


}
