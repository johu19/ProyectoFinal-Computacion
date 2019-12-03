package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.BusClienteRest;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;

public class BusClienteRestTest {

	public final static String REST_URI = "http://localhost:8080/";
	
	private BusClienteRest delegado;
	
	@Mock
	private RestTemplate restTemplate;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		delegado = new BusClienteRest();
		restTemplate = new RestTemplate();
		delegado.setRestTemplate(restTemplate);
	}
	
	@Test
	public void testagregarBus () {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(10));
		bus.setPlaca("ABC");
		bus.setTipo(TipoBus.T);
		
		assertEquals(delegado.agregarBus(bus),bus);
		when(restTemplate.postForEntity(REST_URI+"api/buses/add", bus, Tmio1Bus.class).getBody()).thenReturn(bus);
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
			
		Tmio1Bus[] lista = new Tmio1Bus[2];
		
		lista[0] = bus;
		lista[1] = bus1;
		
		assertEquals(delegado.findAllBuses(),lista);
		when(restTemplate.getForObject(REST_URI + "api/buses/findAll", Tmio1Bus[].class)).thenReturn(lista);
	}
	
	//TODO
	@Test
	public void testobtenerTipos () {
		
	}
	
}
