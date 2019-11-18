package co.edu.icesi.miniproyecto.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.miniproyecto.Taller3Application;
import co.edu.icesi.miniproyecto.daos.Tmio1RutaDao;
import co.edu.icesi.miniproyecto.model.Dia;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@SpringBootTest(classes = Taller3Application.class)
public class TestTmio1RutaDAO {
	
	@Autowired
	private Tmio1RutaDao dao;
	
	    @Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
		public void saveRutaTest() {
			Tmio1Ruta r = new Tmio1Ruta();
			r.setActiva("SI");
			r.setDescripcion("rutica");
			r.setNumero("E21");
			r.setDiaInicio(new BigDecimal(3));
			r.setDiaFin(new BigDecimal(5));
			r.setHoraInicio(new BigDecimal(5));
			r.setHoraFin(new BigDecimal(18));
			dao.save(r);
			assertTrue(dao.findById(r.getId())!=null);
		}
	    
	    @Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	    public void updateRutaTest() {
	    	Tmio1Ruta r = new Tmio1Ruta();
			r.setActiva("NO");
			r.setDescripcion("rutica");
			r.setNumero("A11");
			r.setDiaInicio(new BigDecimal(2));
			r.setDiaFin(new BigDecimal(5));
			r.setHoraInicio(new BigDecimal(10));
			r.setHoraFin(new BigDecimal(16));
			dao.save(r);
			r.setActiva("NO");
			dao.update(r);
			assertTrue(dao.findById(r.getId()).getActiva().equals("NO"));
	    }
	    
	    @Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	    public void findByFechasTest() {
	    	Tmio1Ruta r = new Tmio1Ruta();
	    	r.setActiva("SI");
			r.setDescripcion("la rutisima");
			r.setNumero("P24");
			r.setDiaInicio(new BigDecimal(4));
			r.setDiaFin(new BigDecimal(6));
			r.setHoraInicio(new BigDecimal(9));
			r.setHoraFin(new BigDecimal(14));
			dao.save(r);
			List<Tmio1Ruta> l = dao.findByFechas(new BigDecimal(3),new BigDecimal(7));
			assertTrue(l.size()>0);
	    }
	    
	    
	    @Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	    public void findByHorasTest() {
	    	Tmio1Ruta r = new Tmio1Ruta();
	    	r.setActiva("SI");
			r.setDescripcion("la rutisima");
			r.setNumero("P24");
			r.setDiaInicio(new BigDecimal(3));
			r.setDiaFin(new BigDecimal(7));
			r.setHoraInicio(new BigDecimal(4));
			r.setHoraFin(new BigDecimal(21));
			dao.save(r);
			List<Tmio1Ruta> l = dao.findByHoras(new BigDecimal(3), new BigDecimal(22));
			assertTrue(l.size()>0);
	    }
	    
	    
	    @Test
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	    public void deleteRutaTest() {
	    	Tmio1Ruta r = new Tmio1Ruta();
	    	r.setActiva("SI");
			r.setDescripcion("la rutisima");
			r.setNumero("P24");
			r.setDiaInicio(new BigDecimal(3));
			r.setDiaFin(new BigDecimal(7));
			r.setHoraInicio(new BigDecimal(4));
			r.setHoraFin(new BigDecimal(21));
			dao.save(r);
			dao.delete(r);
			assertTrue(dao.findById(r.getId())==null);
	    }

}
