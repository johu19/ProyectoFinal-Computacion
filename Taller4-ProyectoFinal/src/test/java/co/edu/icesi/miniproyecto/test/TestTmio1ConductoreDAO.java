package co.edu.icesi.miniproyecto.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
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
import co.edu.icesi.miniproyecto.daos.Tmio1BusDao;
import co.edu.icesi.miniproyecto.daos.Tmio1ConductoreDao;
import co.edu.icesi.miniproyecto.daos.Tmio1RutaDao;
import co.edu.icesi.miniproyecto.daos.Tmio1ServicioDao;
import co.edu.icesi.miniproyecto.model.Dia;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@SpringBootTest(classes = Taller3Application.class)
public class TestTmio1ConductoreDAO {

	@Autowired
	private Tmio1ConductoreDao daoCond;

	@Autowired
	private Tmio1BusDao daoBus;

	@Autowired
	private Tmio1ServicioDao daoServ;

	@Autowired
	private Tmio1RutaDao daoRuta;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveConductoreTest() {

		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setCedula("1151963652");
		cond.setNombre("Jose");
		cond.setApellidos("Galvis");
		cond.setFechaNacimiento(new Date(10));
		cond.setFechaContratacion(new Date(20));
		daoCond.save(cond);
		assertTrue(daoCond.findByCedula(cond.getCedula()) != null);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateConductoreTest() {

		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setCedula("1151962308");
		cond.setNombre("Diana");
		cond.setApellidos("Torres");
		cond.setFechaNacimiento(new Date(10));
		cond.setFechaContratacion(new Date(20));
		daoCond.save(cond);
		cond.setApellidos("Galvis");
		daoCond.update(cond);
		assertTrue(daoCond.findByCedula(cond.getCedula()).getApellidos().equals("Galvis"));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteConductoreTest() {

		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setCedula("91213921");
		cond.setNombre("Hugo");
		cond.setApellidos("Galvis");
		cond.setFechaNacimiento(new Date(10));
		cond.setFechaContratacion(new Date(20));
		daoCond.save(cond);
		daoCond.delete(cond);
		assertTrue(daoCond.findByCedula(cond.getCedula()) == null);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByNombreConductoreTest() {

		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setCedula("91213921");
		cond.setNombre("Hugo");
		cond.setApellidos("Galvis");
		cond.setFechaNacimiento(new Date(10));
		cond.setFechaContratacion(new Date(20));
		Tmio1Conductore cond1 = new Tmio1Conductore();
		cond1.setCedula("1134097453");
		cond1.setNombre("Hugo");
		cond1.setApellidos("Clavijo");
		cond1.setFechaNacimiento(new Date(10));
		cond1.setFechaContratacion(new Date(20));
		daoCond.save(cond);
		daoCond.save(cond1);
		List<Tmio1Conductore> res = daoCond.findByNombre("Hugo");
		boolean s = false;
		for (int i = 0; i < res.size() && !s; i++) {
			if (!res.get(i).getNombre().equals("Hugo")) {
				s = true;
			}
		}

		assertTrue(s == false);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findByApellidosConductoreTest() {

		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setCedula("9121392100");
		cond.setNombre("Hugo");
		cond.setApellidos("Galvis Ardila");
		cond.setFechaNacimiento(new Date(10));
		cond.setFechaContratacion(new Date(20));
		Tmio1Conductore cond1 = new Tmio1Conductore();
		cond1.setCedula("113409745300");
		cond1.setNombre("Fernando");
		cond1.setApellidos("Galvis Ardila");
		cond1.setFechaNacimiento(new Date(10));
		cond1.setFechaContratacion(new Date(20));
		daoCond.save(cond);
		daoCond.save(cond1);
		List<Tmio1Conductore> res = daoCond.findByApellidos("Galvis Ardila");
		boolean s = false;
		for (int i = 0; i < res.size() && !s; i++) {
			if (!res.get(i).getApellidos().equals("Galvis Ardila")) {
				s = true;
			}
		}

		assertTrue(s == false);

	}

}
