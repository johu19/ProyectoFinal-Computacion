package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.SitiosRutaClienteRest;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;
import co.edu.icesi.miniproyecto.servicioRest.SitiosRutaServicioRest;

public class SitiosRutaClienteRestTest {

	public final static String REST_URI = "http://localhost:8080/";
	
	private SitiosRutaClienteRest delegado;
	
	private Tmio1Ruta ruta1;
	private Tmio1Ruta ruta2;
	
	private Tmio1Sitio sitio1;
	private Tmio1Sitio sitio2;
	
	@Mock
	private RestTemplate restTemplate;
	
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
	
	//TODO
	public void setupSitios() {
		sitio1 = new Tmio1Sitio();
		
		sitio2 = new Tmio1Sitio();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		delegado = new SitiosRutaClienteRest();
		restTemplate = new RestTemplate();
		delegado.setRestTemplate(restTemplate);
		setupRutas();
		setupSitios();
	}
	
	@Test
	public void testagregarSitiosRuta () {
		Tmio1SitiosRuta sr = new Tmio1SitiosRuta();
		
		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
		id.setIdRuta(ruta1.getId());
		id.setIdSitio(ruta2.getId());
		
		sr.setId(id);
		sr.setPlaneID("0");
		sr.setTmio1Ruta(ruta1);
		sr.setTmio1Sitio(sitio1);
		
		when(restTemplate.postForEntity(REST_URI + "api/sr/add", sr, Tmio1SitiosRuta.class).getBody()).thenReturn(sr);
		assertEquals(delegado.agregarSitiosRuta(sr),sr);
	}
	
	@Test
	public void testfindAllSitiosRuta () {
		Tmio1SitiosRuta sr = new Tmio1SitiosRuta();
		
		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
		id.setIdRuta(ruta1.getId());
		id.setIdSitio(ruta2.getId());
		
		sr.setId(id);
		sr.setPlaneID("0");
		sr.setTmio1Ruta(ruta1);
		sr.setTmio1Sitio(sitio1);
		
		Tmio1SitiosRuta sr1 = new Tmio1SitiosRuta();
		
		Tmio1SitiosRutaPK id1 = new Tmio1SitiosRutaPK();
		id.setIdRuta(ruta2.getId());
		id.setIdSitio(ruta1.getId());
		
		sr1.setId(id1);
		sr1.setPlaneID("0");
		sr1.setTmio1Ruta(ruta2);
		sr1.setTmio1Sitio(sitio2);
		
		Tmio1SitiosRuta[] lista = new Tmio1SitiosRuta[2];
		lista[0]=sr;
		lista[1]=sr1;
		
		when(restTemplate.getForObject(REST_URI + "api/sr/findAll", Tmio1SitiosRuta[].class)).thenReturn(lista);
		assertEquals(delegado.findAllSitiosRuta(),lista);
	}
	
	//TODO
	@Test
	public void testborrarSitiosRuta () {

	}
	
	@Test
	public void testactualizarSitiosRuta () {
		Tmio1SitiosRuta sr = new Tmio1SitiosRuta();
		
		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
		id.setIdRuta(ruta1.getId());
		id.setIdSitio(ruta2.getId());
		
		sr.setId(id);
		sr.setPlaneID("0");
		sr.setTmio1Ruta(ruta1);
		sr.setTmio1Sitio(sitio1);
		
		when(restTemplate.patchForObject(REST_URI + "api/sr/update", sr, Tmio1SitiosRuta.class)).thenReturn(sr);
		assertEquals(delegado.actualizarSitiosRuta(sr),sr);
	}
	
	//TODO
	@Test
	public void testfindByPlanedId () {
		Tmio1SitiosRuta sr = new Tmio1SitiosRuta();
		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
		id.setIdRuta(ruta1.getId());
		id.setIdSitio(ruta2.getId());
		sr.setId(id);
		sr.setPlaneID("0");
		sr.setTmio1Ruta(ruta1);
		sr.setTmio1Sitio(sitio1);
		
		
	}
}
