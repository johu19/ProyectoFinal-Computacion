package co.edu.icesi.miniproyecto.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

@Repository
@Scope("singleton")
public class Tmio1ServicioDao implements ITmio1ServicioDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Servicio serv) {
		entityManager.persist(serv);
	}

	@Override
	public void delete(Tmio1Servicio serv) {
		entityManager.remove(serv);
	}

	@Override
	public Tmio1Servicio findById(Tmio1ServicioPK pk) {
		return entityManager.find(Tmio1Servicio.class, pk);
	}

	@Override
	public void update(Tmio1Servicio serv) {
		entityManager.merge(serv);
	}

	@Override
	public List<Tmio1Servicio> findByPlaneId(String id) {
		String jpql = "SELECT a FROM Tmio1Servicio a WHERE a.planeID='"+id+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Servicio> findAll() {
		String jpql = "SELECT a FROM Tmio1Servicio a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Object[]> findConductoresWithAmountOfServicios(Date fechaLimite) {
		
		List<Object[]> res = new ArrayList<Object[]>();
		
		Query q = entityManager.createQuery("SELECT c FROM Tmio1Servicio s JOIN s.tmio1Conductore c WHERE "
				+ "s.fechaServicio <= :date ORDER BY s.fechaServicio DESC ");
		
		q.setParameter("date", fechaLimite, TemporalType.TIMESTAMP);
		
		List<Tmio1Conductore> partialResult1 = q.getResultList();
		distinct(partialResult1);
		
		
		Query q2 = entityManager.createQuery("SELECT c, COUNT(c) FROM Tmio1Servicio s JOIN s.tmio1Conductore c WHERE s.fechaServicio <= :date GROUP BY c");
		
		q2.setParameter("date", fechaLimite, TemporalType.TIMESTAMP);
		
		List<Object[]> partialResult2 = q2.getResultList();
		
		for(int i =0 ; i<partialResult1.size();i++) {
			Object[] obj = new Object[2];
			obj[0] = partialResult1.get(i);
			boolean condicion = false;
			for(int j = 0 ; j<partialResult2.size() && !condicion;j++) {
				Tmio1Conductore c = (Tmio1Conductore)partialResult2.get(j)[0];
				if(c.getCedula().equals(partialResult1.get(i).getCedula())) {
					condicion = true;
					obj[1] = (Long)partialResult2.get(j)[1];
				}
			}
			res.add(obj);
		}
		
		return res;
	}

	

	@Override
	public List<Tmio1Ruta> findRutasWithLessThan10(Date date) {
		List<Tmio1Ruta> rutas= new ArrayList<Tmio1Ruta>();
		
		Query q = entityManager.createQuery("SELECT r, COUNT(r) FROM Tmio1Servicio s JOIN s.tmio1Ruta r WHERE s.fechaServicio = :date GROUP BY r");
		
		q.setParameter("date", date, TemporalType.TIMESTAMP);
		
		List<Object[]> partialResult= q.getResultList();
		
		for (Object[] obj: partialResult) {
			Tmio1Ruta ruta= (Tmio1Ruta) obj[0];
			int cantidad= ((Number) obj[1]).intValue();
			if( cantidad<10 && cantidad >0) {
				rutas.add(ruta);
			}
		}
		return rutas;
	}

	
	
	@Override
	public List<Tmio1Bus> findBusesWithMoreThanOneServicio(Date date) {
		
		List<Tmio1Bus> buses = new ArrayList<>();
		
		Query q = entityManager.createQuery("SELECT b, COUNT(b) FROM Tmio1Servicio s JOIN s.tmio1Bus b WHERE s.fechaServicio = :date  GROUP BY b");
		
		q.setParameter("date", date, TemporalType.TIMESTAMP);
		
		List<Object[]> partialResult= q.getResultList();
		
		for (Object[] obj: partialResult) {
			Tmio1Bus bus= (Tmio1Bus) obj[0];
			int cantidad= ((Number) obj[1]).intValue();
			if( cantidad>1) {
				buses.add(bus);
			}
		}
		
		return buses;
	}

	@Override
	public void clearEM() {
		entityManager.clear();
	}
	
	private void distinct(List<Tmio1Conductore> list) {
		for(int i=list.size()-1;i>=0;i--) {
			Tmio1Conductore d = list.get(i);
			for(int j=0;j<i ;j++) {
				Tmio1Conductore d1 = list.get(j);
				if(d.equals(d1)) {
					list.remove(i);
					i--;
				}
			}
			
		}
	}
	
}
