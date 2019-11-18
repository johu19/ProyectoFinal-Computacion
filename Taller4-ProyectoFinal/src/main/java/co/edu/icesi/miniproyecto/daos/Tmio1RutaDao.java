package co.edu.icesi.miniproyecto.daos;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Dia;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

@Repository
@Scope("singleton")
public class Tmio1RutaDao implements ITmio1RutaDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Ruta ruta) {
		entityManager.persist(ruta);

	}

	@Override
	public void update(Tmio1Ruta ruta) {
		entityManager.merge(ruta);
	}

	@Override
	public void delete(Tmio1Ruta ruta) {
		entityManager.remove(ruta);
	}

	@Override
	public Tmio1Ruta findById(Integer id) {
		return entityManager.find(Tmio1Ruta.class, id);
	}

	@Override
	public List<Tmio1Ruta> findByFechas(BigDecimal inicio, BigDecimal fin) {
		String jpql = "Select r from Tmio1Ruta r Where r.diaInicio >= '" + inicio + "'and r.diaFin <= '" + fin+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findByHoras(BigDecimal inicio, BigDecimal fin) {
		String jpql = "SELECT r FROM Tmio1Ruta r WHERE r.horaInicio >= '" + inicio + "' AND r.horaFin <= '"
				+ fin + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findAll() {
		String jpql = "SELECT a FROM Tmio1Ruta a";
		return entityManager.createQuery(jpql).getResultList();
	}


	
}
