package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.SitioClienteRest;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.model.Tmio1ServiciosSitio;
import co.edu.icesi.miniproyecto.model.Tmio1ServiciosSitioPK;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.servicioRest.SitioServicioRest;

public class SitioClienteRestTest {

	public final static String REST_URI = "http://localhost:8080/";
	
	private SitioClienteRest delegado;
	
	private Tmio1Bus bus1;
	private Tmio1Bus bus2;
	
	private Tmio1Conductore conductore1;
	private Tmio1Conductore conductore2;
	
	private Tmio1Ruta ruta1;
	private Tmio1Ruta ruta2;
	
	private Tmio1ServicioPK servicioPk1;
	private Tmio1ServicioPK servicioPk2;
	private Tmio1ServicioPK servicioPk3;
	
	private Tmio1Servicio serv1;
	private Tmio1Servicio serv2; 
	
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
		ruta2.setNumero("T31");
	}
	
	public void setupPks () throws ParseException {
		servicioPk1 = new Tmio1ServicioPK();
		servicioPk1.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("10/22/2019"));
		servicioPk1.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("12/22/2019"));
		
		servicioPk2 = new Tmio1ServicioPK();
		servicioPk2.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("10/22/2019"));
		servicioPk2.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		
		servicioPk3 = new Tmio1ServicioPK();
		servicioPk3.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("08/22/2019"));
		servicioPk3.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("12/22/2019"));
		
	}
	
	public void setupServicios() {
		serv1 = new Tmio1Servicio();
		serv1.setPlaneID("0");
		serv1.setTmio1Bus(bus1);
		serv1.setTmio1Conductore(conductore1);
		serv1.setTmio1Ruta(ruta1);
		serv1.setId(servicioPk1);
		
		serv2 = new Tmio1Servicio();
		serv2.setPlaneID("2");
		serv2.setTmio1Bus(bus2);
		serv2.setTmio1Conductore(conductore2);
		serv2.setTmio1Ruta(ruta2);
		serv2.setId(servicioPk2);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void init() throws ParseException {
		delegado = new SitioClienteRest();
		restTemplate = new RestTemplate();
		delegado.setRestTemplate(restTemplate);
		setupBuses();
		setupConductores();
		setupRutas();
		setupPks();
		setupServicios();
	}
	
	@Test
	public void testagregarSitio () {
		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setDescripcion("aaa");
		sitio.setId(1);
		sitio.setNombre("sitio");
//		sitio.setTmio1ServiciosSitios(tmio1ServiciosSitios);
//		sitio.setTmio1SitiosRutas1(tmio1SitiosRutas1);
//		sitio.setTmio1SitiosRutas2(tmio1SitiosRutas2);
		
		when(restTemplate.postForEntity(REST_URI+"api/sitios/add",sitio, Tmio1Sitio.class).getBody()).thenReturn(sitio);
		assertEquals(delegado.agregarSitio(sitio), sitio);
	}
	
	@Test
	public void testfindAllSitios () {
		
		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setDescripcion("bien");
		sitio.setId(0);
		sitio.setNombre("pacho");
		
		Tmio1Sitio[] sitios = new Tmio1Sitio[1];
		sitios[0]=sitio;
		
		restTemplate.postForEntity(REST_URI+"api/sitios/add",sitio, Tmio1Sitio.class).getBody();
		delegado.agregarSitio(sitio);
		
		when(restTemplate.getForObject(REST_URI+"api/sitios/findAll", Tmio1Sitio[].class)).thenReturn(sitios);
		assertEquals(delegado.findAllSitios(), sitios);
		
	}
	
	@Test
	public void testborrarSitio () {
		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setDescripcion("aaa");
		sitio.setId(1);
		sitio.setNombre("sitio");
		
		restTemplate.postForEntity(REST_URI+"api/sitios/add",sitio, Tmio1Sitio.class).getBody();
		restTemplate.delete(REST_URI+"api/sitios/borrar/"+sitio.getId());
		when(restTemplate.getForObject(REST_URI+"api/sitios/findById/"+sitio.getId(), Tmio1Sitio.class)).thenReturn(null);

		delegado.agregarSitio(sitio);
		delegado.borrarSitio(sitio.getId());
		assertEquals(delegado.findById(sitio.getId()), null);
	}
	
	@Test
	public void testactualizarSitio () {
		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setDescripcion("bien");
		sitio.setId(0);
		sitio.setNombre("pacho");
		
		restTemplate.postForEntity(REST_URI+"api/sitios/add",sitio, Tmio1Sitio.class).getBody();
		delegado.agregarSitio(sitio);
		
		when(restTemplate.patchForObject(REST_URI+"api/sitios/update", sitio, Tmio1Sitio.class)).thenReturn(sitio);
		assertEquals(delegado.actualizarSitio(sitio), sitio);
	}
	
	@Test
	public void testfindById () {
		Tmio1Sitio sitio = new Tmio1Sitio();
		sitio.setDescripcion("bien");
		sitio.setId(0);
		sitio.setNombre("pacho");
		
		Tmio1Sitio sitio1 = new Tmio1Sitio();
		sitio1.setDescripcion("bien");
		sitio1.setId(1);
		sitio1.setNombre("pacho");
		
		Tmio1Sitio[] sitios = new Tmio1Sitio[2];
		sitios[0]=sitio;
		sitios[1]=sitio1;
		
		restTemplate.postForEntity(REST_URI+"api/sitios/add",sitio, Tmio1Sitio.class).getBody();
		delegado.agregarSitio(sitio);
		restTemplate.postForEntity(REST_URI+"api/sitios/add",sitio, Tmio1Sitio.class).getBody();
		delegado.agregarSitio(sitio1);
		
		when(restTemplate.getForObject(REST_URI+"api/sitios/findById/"+sitio.getId(), Tmio1Sitio.class)).thenReturn(sitio);
		assertEquals(delegado.findById(sitio.getId()), sitio);
	}
	
}
