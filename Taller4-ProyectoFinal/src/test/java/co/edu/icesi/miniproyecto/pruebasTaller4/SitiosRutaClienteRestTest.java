package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.SitiosRutaClienteRest;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;
import co.edu.icesi.miniproyecto.servicioRest.SitiosRutaServicioRest;

public class SitiosRutaClienteRestTest {

	private SitiosRutaClienteRest delegado;
	
	private Tmio1Ruta ruta1;
	private Tmio1Ruta ruta2;
	
	private Tmio1Sitio sitio1;
	private Tmio1Sitio sitio2;
	
	@Mock
	private SitiosRutaServicioRest mock;
	
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
	
	public void setupSitios() {
		sitio1 = new Tmio1Sitio();
		
		sitio2 = new Tmio1Sitio();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		delegado = new SitiosRutaClienteRest();
		setupRutas();
	}
	
	@Test
	public void testagregarSitiosRuta () {
		Tmio1SitiosRuta sr = new Tmio1SitiosRuta();
		
		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
		id.setIdRuta(ruta1.getId());
		id.setIdSitio(ruta2.getId());
		
		sr.setId(id);
		sr.setPlaneID("0");
		sr.setTmio1Ruta1(ruta1);
		sr.setTmio1Ruta2(ruta2);
		sr.setTmio1Sitio1(sitio1);
		sr.setTmio1Sitio2(sitio2);
		
		when(mock.agregarSitiosRuta(sr)).thenReturn(sr);
	}
	
	@Test
	public void testfindAllSitiosRuta () {
		Tmio1SitiosRuta sr = new Tmio1SitiosRuta();
		
		Tmio1SitiosRutaPK id = new Tmio1SitiosRutaPK();
		id.setIdRuta(ruta1.getId());
		id.setIdSitio(ruta2.getId());
		
		sr.setId(id);
		sr.setPlaneID("0");
		sr.setTmio1Ruta1(ruta1);
		sr.setTmio1Ruta2(ruta2);
		sr.setTmio1Sitio1(sitio1);
		sr.setTmio1Sitio2(sitio2);
		
		Tmio1SitiosRuta sr1 = new Tmio1SitiosRuta();
		
		Tmio1SitiosRutaPK id1 = new Tmio1SitiosRutaPK();
		id.setIdRuta(ruta2.getId());
		id.setIdSitio(ruta1.getId());
		
		sr1.setId(id1);
		sr1.setPlaneID("0");
		sr1.setTmio1Ruta1(ruta2);
		sr1.setTmio1Ruta2(ruta1);
		sr1.setTmio1Sitio1(sitio2);
		sr1.setTmio1Sitio2(sitio1);
		
		List<Tmio1SitiosRuta> lista = new ArrayList<Tmio1SitiosRuta>();
		lista.add(sr);
		lista.add(sr1);
		
		when(mock.findAllSitiosRuta()).thenReturn(lista);
	}
	
	@Test
	public void testborrarSitiosRuta () {

	}
	
	@Test
	public void testactualizarSitiosRuta () {

	}
	
	@Test
	public void testfindByPlanedId () {

	}
}
