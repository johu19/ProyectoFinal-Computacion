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
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@SpringBootTest(classes = Taller3Application.class)
public class TestTmio1ServicioDAO {

	@Autowired
	private Tmio1ServicioDao daoServ;

	@Autowired
	private Tmio1RutaDao daoRuta;

	@Autowired
	private Tmio1BusDao daoBus;

	@Autowired
	private Tmio1ConductoreDao daoCond;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveServicioTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.T);
		bus.setCapacidad(new BigDecimal(5));
		bus.setMarca("Porsche");
		bus.setPlaca("IZU3391");
		bus.setModelo(new BigDecimal(2016));
		daoBus.save(bus);

		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setCedula("1151963652");
		cond.setNombre("Jose");
		cond.setApellidos("Galvis");
		cond.setFechaNacimiento(new Date(10));
		cond.setFechaContratacion(new Date(20));
		daoCond.save(cond);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("SI");
		ruta.setDescripcion("rutica");
		ruta.setNumero("E21");
		ruta.setDiaInicio(new BigDecimal(3));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(5));
		ruta.setHoraFin(new BigDecimal(18));
		daoRuta.save(ruta);

		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setTmio1Bus(bus);
		serv.setTmio1Conductore(cond);
		serv.setTmio1Ruta(ruta);
		serv.setFechaServicio(new Date(10000));

		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setIdBus(serv.getTmio1Bus().getId());
		pk.setIdRuta(serv.getTmio1Ruta().getId());
		pk.setCedulaConductor(serv.getTmio1Conductore().getCedula());
		pk.setFechaInicio(new Date());
		pk.setFechaFin(new Date());

		serv.setId(pk);
		serv.setPlaneID(pk.getIdBus() + "_" + pk.getIdRuta() + "_" + pk.getCedulaConductor() + "_"
				+ serv.getFechaServicio().toString());
		

		daoServ.save(serv);
		
		
		

		assertTrue(daoServ.findById(serv.getId()).equals(serv));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteServicioTest() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.T);
		bus.setCapacidad(new BigDecimal(5));
		bus.setMarca("Porsche");
		bus.setPlaca("IZU3392");
		bus.setModelo(new BigDecimal(2016));
		daoBus.save(bus);

		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setCedula("1151962308");
		cond.setNombre("Diana");
		cond.setApellidos("Torres");
		cond.setFechaNacimiento(new Date(10));
		cond.setFechaContratacion(new Date(20));
		daoCond.save(cond);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("SI");
		ruta.setDescripcion("rutica");
		ruta.setNumero("E21");
		ruta.setDiaInicio(new BigDecimal(3));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(5));
		ruta.setHoraFin(new BigDecimal(18));
		daoRuta.save(ruta);

		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setTmio1Bus(bus);
		serv.setTmio1Conductore(cond);
		serv.setTmio1Ruta(ruta);
		serv.setFechaServicio(new Date(10000));

		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setIdBus(serv.getTmio1Bus().getId());
		pk.setIdRuta(serv.getTmio1Ruta().getId());
		pk.setCedulaConductor(serv.getTmio1Conductore().getCedula());
		pk.setFechaInicio(new Date());
		pk.setFechaFin(new Date());

		serv.setId(pk);

		daoServ.save(serv);
		daoServ.delete(serv);

		assertTrue(daoServ.findById(serv.getId()) == null);
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findBusesWithMoreThanOneServicioTest() {
		setup1();
		Date d = new Date(20);
		List<Tmio1Bus> consulta = daoServ.findBusesWithMoreThanOneServicio(d);
		assertTrue(consulta.size()==1 && consulta.get(0).getPlaca().equals("IZU339"));
		
	}
	
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findRutasWithLessThan10Test() {
		Tmio1Ruta r = setup2();
		Date d = new Date(10);
		List<Tmio1Ruta> consulta = daoServ.findRutasWithLessThan10(d);
		assertTrue(consulta.size()==1 && consulta.get(0).getId().equals(r.getId()));
		
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findConductoresWithAmountOfServiciosTest() {
		setup3();
		Date d = new Date(4);
		List<Object[]> consulta = daoServ.findConductoresWithAmountOfServicios(d);
		for (Object[] objects : consulta) {
			Tmio1Conductore conductor = (Tmio1Conductore) objects[0];
			Long num = (Long) objects[1];
			System.out.println("Conductor: "+ conductor.getNombre());
			System.out.println("Cantidad de servicios: "+ num);
		}
		assertTrue(consulta.size()==2);
	}
	
	
	

	private void setup1() {
		
		daoServ.clearEM();

		

		Tmio1Conductore cond1 = new Tmio1Conductore();
		cond1.setCedula("115196365211");
		cond1.setNombre("Jose");
		cond1.setApellidos("Galvis");
		cond1.setFechaNacimiento(new Date(10));
		cond1.setFechaContratacion(new Date(20));
		daoCond.save(cond1);

		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.T);
		bus.setCapacidad(new BigDecimal(5));
		bus.setMarca("Porsche");
		bus.setPlaca("IZU339");
		bus.setModelo(new BigDecimal(2016));
		daoBus.save(bus);
		
		

		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setTipo(TipoBus.A);
		bus1.setCapacidad(new BigDecimal(5));
		bus1.setMarca("Volvo");
		bus1.setPlaca("DMS467");
		bus1.setModelo(new BigDecimal(2018));
		daoBus.save(bus1);

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("si");
		ruta.setDescripcion("E21");
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setDiaInicio(new BigDecimal(2));
		ruta.setNumero("10");
		ruta.setHoraInicio(new BigDecimal(5));
		ruta.setHoraFin(new BigDecimal(20));
		daoRuta.save(ruta);
		
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setActiva("no");
		ruta1.setDescripcion("A11");
		ruta1.setDiaFin(new BigDecimal(7));
		ruta1.setDiaInicio(new BigDecimal(2));
		ruta1.setNumero("10");
		ruta1.setHoraInicio(new BigDecimal(5));
		ruta1.setHoraFin(new BigDecimal(20));
		daoRuta.save(ruta1);

		Tmio1Servicio ser = new Tmio1Servicio();
		ser.setTmio1Bus(bus1);
		ser.setTmio1Conductore(cond1);
		ser.setTmio1Ruta(ruta);
		ser.setFechaServicio(new Date(10));
		setPK(ser);

		Tmio1Servicio ser1 = new Tmio1Servicio();
		ser1.setTmio1Bus(bus);
		ser1.setTmio1Conductore(cond1);
		ser1.setTmio1Ruta(ruta);
		ser1.setFechaServicio(new Date(20));
		setPK(ser1);

		Tmio1Servicio ser2 = new Tmio1Servicio();
		ser2.setTmio1Bus(bus);
		ser2.setTmio1Conductore(cond1);
		ser2.setTmio1Ruta(ruta1);
		ser2.setFechaServicio(new Date(20));
		setPK(ser2);

		daoServ.save(ser);
		daoServ.save(ser1);
		daoServ.save(ser2);

	}
	
	
	private Tmio1Ruta setup2() {
		daoServ.clearEM();
		
		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setCedula("113409745300");
		cond.setNombre("Fernando");
		cond.setApellidos("Galvis Ardila");
		cond.setFechaNacimiento(new Date(10));
		cond.setFechaContratacion(new Date(20));
		daoCond.save(cond);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setTipo(TipoBus.A);
		bus.setCapacidad(new BigDecimal(5));
		bus.setMarca("Kia");
		bus.setPlaca("CWR009");
		bus.setModelo(new BigDecimal(2018));
		daoBus.save(bus);
		
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setTipo(TipoBus.A);
		bus1.setCapacidad(new BigDecimal(5));
		bus1.setMarca("Chevrolet");
		bus1.setPlaca("MSX009");
		bus1.setModelo(new BigDecimal(2018));
		daoBus.save(bus1);
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("si");
		ruta.setDescripcion("E21");
		ruta.setDiaFin(new BigDecimal(7));
		ruta.setDiaInicio(new BigDecimal(2));
		ruta.setNumero("10");
		ruta.setHoraInicio(new BigDecimal(5));
		ruta.setHoraFin(new BigDecimal(20));
		daoRuta.save(ruta);
		
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setActiva("si");
		ruta1.setDescripcion("E21");
		ruta1.setDiaFin(new BigDecimal(7));
		ruta1.setDiaInicio(new BigDecimal(2));
		ruta1.setNumero("10");
		ruta1.setHoraInicio(new BigDecimal(5));
		ruta1.setHoraFin(new BigDecimal(20));
		daoRuta.save(ruta1);
		
		Tmio1Servicio ser = new Tmio1Servicio();
		ser.setTmio1Bus(bus);
		ser.setTmio1Conductore(cond);
		ser.setTmio1Ruta(ruta);
		ser.setFechaServicio(new Date(10));
		setPK(ser);
		
		
		Tmio1Servicio ser1 = new Tmio1Servicio();
		ser1.setTmio1Bus(bus1);
		ser1.setTmio1Conductore(cond);
		ser1.setTmio1Ruta(ruta);
		ser1.setFechaServicio(new Date(10));
		setPK(ser1);
		
		Tmio1Servicio ser2 = new Tmio1Servicio();
		ser2.setTmio1Bus(bus1);
		ser2.setTmio1Conductore(cond);
		ser2.setTmio1Ruta(ruta1);
		ser2.setFechaServicio(new Date(15));
		setPK(ser2);
		
		Tmio1Servicio ser3 = new Tmio1Servicio();
		ser3.setTmio1Bus(bus);
		ser3.setTmio1Conductore(cond);
		ser3.setTmio1Ruta(ruta1);
		ser3.setFechaServicio(new Date(15));
		setPK(ser3);
		
		daoServ.save(ser);
		daoServ.save(ser1);
		daoServ.save(ser2);
		daoServ.save(ser3);
		
		return ruta;
		
	}

	
	private void setup3() {
		daoServ.clearEM();
		
		Tmio1Conductore cond = new Tmio1Conductore();
		cond.setCedula("123");
		cond.setApellidos("Perez");
		cond.setNombre("Pepito");
		cond.setFechaNacimiento(new Date(3));
		cond.setFechaContratacion(new Date(30));
		daoCond.save(cond);
		
		
		Tmio1Conductore cond1 = new Tmio1Conductore();
		cond1.setCedula("321");
		cond1.setApellidos("Ruiz");
		cond1.setNombre("Paco");
		cond1.setFechaNacimiento(new Date(6));
		cond1.setFechaContratacion(new Date(25));
		daoCond.save(cond1);
		
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setActiva("si");
		ruta.setDescripcion("T31");
		ruta.setDiaInicio(new BigDecimal(3));
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setHoraInicio(new BigDecimal(7));
		ruta.setHoraFin(new BigDecimal(18));
		ruta.setNumero("5");
		daoRuta.save(ruta);
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setMarca("Renault");
		bus.setModelo(new BigDecimal(2019));
		bus.setPlaca("XXX000");
		bus.setTipo(TipoBus.A);
		daoBus.save(bus);
		
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setCapacidad(new BigDecimal(10));
		bus1.setMarca("Jeep");
		bus1.setModelo(new BigDecimal(2019));
		bus1.setPlaca("ABC123");
		bus1.setTipo(TipoBus.A);
		daoBus.save(bus1);
		
		
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setFechaServicio(new Date(5));
		serv.setTmio1Bus(bus);
		serv.setTmio1Ruta(ruta);
		serv.setTmio1Conductore(cond);
		setPK(serv);
		
		Tmio1Servicio serv1 = new Tmio1Servicio();
		serv1.setFechaServicio(new Date(2));
		serv1.setTmio1Bus(bus1);
		serv1.setTmio1Ruta(ruta);
		serv1.setTmio1Conductore(cond);
		setPK(serv1);
		
		
		Tmio1Servicio serv2 = new Tmio1Servicio();
		serv2.setFechaServicio(new Date(1));
		serv2.setTmio1Bus(bus);
		serv2.setTmio1Ruta(ruta);
		serv2.setTmio1Conductore(cond1);
		setPK(serv2);
		
		
		Tmio1Servicio serv3 = new Tmio1Servicio();
		serv3.setFechaServicio(new Date(1));
		serv3.setTmio1Bus(bus1);
		serv3.setTmio1Ruta(ruta);
		serv3.setTmio1Conductore(cond1);
		setPK(serv3);
		
		
		daoServ.save(serv);
		daoServ.save(serv1);
		daoServ.save(serv2);
		daoServ.save(serv3);
		
		
		
	}
	
	
	private void setPK(Tmio1Servicio servicio) {
		Tmio1ServicioPK pk = new Tmio1ServicioPK();
		pk.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
		pk.setIdBus(servicio.getTmio1Bus().getId());
		pk.setIdRuta(servicio.getTmio1Ruta().getId());
		pk.setFechaInicio(servicio.getFechaServicio());
		pk.setFechaFin(new Date());
		servicio.setPlaneID(pk.getIdBus() + "_" + pk.getIdRuta() + "_" + pk.getCedulaConductor() + "_"
				+ servicio.getFechaServicio().toString());
		servicio.setId(pk);
	}

}
