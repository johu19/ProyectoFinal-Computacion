package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.BusClienteRest;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.servicioRest.BusServicioRest;

public class BusClienteRestTest {

	private BusClienteRest delegado;
	
	@Mock
	private BusServicioRest mock;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		delegado = new BusClienteRest();
	}
	
	@Test
	public void testagregarBus () {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(10));
		bus.setPlaca("ABC");
		bus.setTipo(TipoBus.T);
		when(mock.agregarBus(bus)).thenReturn(bus);
	}
	
	@Test
	public void testfindAllBuses () {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(10));
		bus.setPlaca("ABC");
		bus.setTipo(TipoBus.T);
		
		Tmio1Bus bus1 = new Tmio1Bus();
		bus1.setId(1);
		bus1.setMarca("Volkswagen");
		bus1.setModelo(new BigDecimal(11));
		bus1.setPlaca("BCD");
		bus1.setTipo(TipoBus.P);
			
		List<Tmio1Bus> lista = new ArrayList<Tmio1Bus>();
		lista.add(bus);
		lista.add(bus1);
		
		when(mock.findAll()).thenReturn(lista);
	}
	
	@Test
	public void testobtenerTipos () {
		
	}
	
	
}
