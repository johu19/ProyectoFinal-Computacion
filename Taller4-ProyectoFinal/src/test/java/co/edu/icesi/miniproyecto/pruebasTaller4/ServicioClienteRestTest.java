package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.ServicioClienteRest;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.servicioRest.Servicio_ServicioRest;

public class ServicioClienteRestTest {

	public final static String REST_URI = "http://localhost:8080/";
	
	private ServicioClienteRest delegado;
	
	private Tmio1Bus bus1;
	private Tmio1Bus bus2;
	
	private Tmio1Conductore conductore1;
	private Tmio1Conductore conductore2;
	
	private Tmio1Ruta ruta1;
	private Tmio1Ruta ruta2;
	
	private Tmio1ServicioPK servicioPk1;
	private Tmio1ServicioPK servicioPk2;
	private Tmio1ServicioPK servicioPk3;
	
	@Mock
	private RestTemplate restTemplate;
	
	public void setupBuses () {
		bus1 = new Tmio1Bus();
		bus1.setId(0);
		bus1.setMarca("Volkswagen");
		bus1.setModelo(new BigDecimal(10));
		bus1.setPlaca("ABC");
		bus1.setTipo(TipoBus.T);
		
		bus2 = new Tmio1Bus();
		bus2.setId(1);
		bus2.setMarca("Volkswagen");
		bus2.setModelo(new BigDecimal(10));
		bus2.setPlaca("DEF");
		bus2.setTipo(TipoBus.P);
	}
	
	public void setupConductores() throws ParseException{
		conductore1 = new Tmio1Conductore();
		conductore1.setCedula("1");
		conductore1.setApellidos("a");
		conductore1.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductore1.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductore1.setNombre("Arroyo");
		
		conductore2 = new Tmio1Conductore();
		conductore2.setCedula("2");
		conductore2.setApellidos("a");
		conductore2.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductore2.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductore2.setNombre("Arroyo");
	}
	
	public void setupRutas () {
		ruta1 = new Tmio1Ruta();
		ruta1.setId(0);
		ruta1.setActiva("Y");
		ruta1.setDescripcion("test");
		ruta1.setDiaFin(new BigDecimal(5));
		ruta1.setDiaInicio(new BigDecimal(1));
		ruta1.setHoraFin(new BigDecimal(82800));
		ruta1.setHoraInicio(new BigDecimal(18000));
		ruta1.setNumero("T31");

		ruta2 = new Tmio1Ruta();
		ruta2.setId(1);
		ruta2.setActiva("Y");
		ruta2.setDescripcion("test");
		ruta2.setDiaFin(new BigDecimal(5));
		ruta2.setDiaInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal(82800));
		ruta2.setHoraInicio(new BigDecimal(18000));
		ruta2.setNumero("E21");
	}
	
	public void setupPks () throws ParseException {
		servicioPk1 = new Tmio1ServicioPK();
		servicioPk1.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("10/22/2019"));
		servicioPk1.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("12/22/2019"));
		servicioPk1.setCedulaConductor(conductore1.getCedula());
		servicioPk1.setIdBus(bus1.getId());
		servicioPk1.setIdRuta(ruta1.getId());
		
		servicioPk2 = new Tmio1ServicioPK();
		servicioPk2.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("10/22/2019"));
		servicioPk2.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		servicioPk2.setCedulaConductor(conductore2.getCedula());
		servicioPk2.setIdBus(bus2.getId());
		servicioPk2.setIdRuta(ruta2.getId());
		
		servicioPk3 = new Tmio1ServicioPK();
		servicioPk3.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("08/22/2019"));
		servicioPk3.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("12/22/2019"));
		servicioPk3.setCedulaConductor(conductore2.getCedula());
		servicioPk3.setIdBus(bus2.getId());
		servicioPk3.setIdRuta(ruta2.getId());
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void init() throws ParseException {
		delegado = new ServicioClienteRest();
		setupBuses();
		setupConductores();
		setupRutas();
		setupPks();
	}
	
	@Test
	public void testagregarServicio () {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setPlaneID("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		
		assertEquals(delegado.agregarServicio(serv),serv);
		when(restTemplate.postForEntity(REST_URI + "api/servicios/add", serv, Tmio1Servicio.class).getBody()).thenReturn(serv);
	}
	
	@Test
	public void testfindAllServicios () {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setPlaneID("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		
		Tmio1Servicio serv1 = new Tmio1Servicio();
		serv1.setPlaneID("1");
		serv1.setTmio1Bus(bus2);
		serv1.setTmio1Conductore(conductore2);
		serv1.setTmio1Ruta(ruta2);
		serv1.setId(servicioPk2);
		
		Tmio1Servicio[] lista = new Tmio1Servicio[2];
		lista[0]=serv;
		lista[1]=serv1;
		
		assertEquals(delegado.findAllServicios(),lista);
		when(restTemplate.getForObject(REST_URI + "api/servicios/findAll", Tmio1Servicio[].class)).thenReturn(lista);
	}
	
	//TODO
	@Test
	public void testborrarServicio () {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setPlaneID("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		
	}
	
	//TODO
	@Test
	public void testfindByDate () {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setPlaneID("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
	}
	
	@Test
	public void testactualizarServicio () {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setPlaneID("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		
		assertEquals(delegado.actualizarServicio(serv),serv);
		when(restTemplate.patchForObject(REST_URI + "api/servicios/update", serv, Tmio1Servicio.class)).thenReturn(serv);
	}
	
	@Test
	public void testfindByPlaneID () {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setPlaneID("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		
		assertEquals(delegado.findByPlaneID(serv.getPlaneID()),serv);
		when( restTemplate.getForObject(REST_URI + "api/servicios/findByPlaneID/" + serv.getPlaneID(),Tmio1Servicio.class)).thenReturn(serv);

	}
	
}
