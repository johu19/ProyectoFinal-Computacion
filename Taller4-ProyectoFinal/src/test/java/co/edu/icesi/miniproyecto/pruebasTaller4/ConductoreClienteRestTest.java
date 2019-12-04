package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.ConductoreClienteRest;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.servicioRest.ConductoreServicioRest;

public class ConductoreClienteRestTest {

	public final static String REST_URI = "http://localhost:8080/";
	
	private ConductoreClienteRest delegado;
	
	@Mock
	private RestTemplate restTemplate;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		delegado = new ConductoreClienteRest();
		restTemplate = new RestTemplate();
		delegado.setRestTemplate(restTemplate);
	}
	
	@Test
	public void testagregarConductor () throws ParseException {
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("1");
		conductor.setApellidos("a");
		conductor.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductor.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductor.setNombre("Arroyo");
		
		when(restTemplate.postForEntity(REST_URI+"api/conductore/add", conductor, Tmio1Conductore.class).getBody()).thenReturn(conductor);
		assertEquals(delegado.agregarConductor(conductor),conductor);
	}
	
	@Test
	public void findAllConductore () throws ParseException {
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("1");
		conductor.setApellidos("a");
		conductor.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductor.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductor.setNombre("Arroyo");
		
		Tmio1Conductore conductor1 = new Tmio1Conductore();
		conductor1.setCedula("2");
		conductor1.setApellidos("b");
		conductor1.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductor1.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductor1.setNombre("Rigo");
		
		Tmio1Conductore[] lista = new Tmio1Conductore[2];
		lista[0]=conductor;
		lista[1]=conductor1;
		
		restTemplate.postForEntity(REST_URI+"api/conductore/add", conductor, Tmio1Conductore.class).getBody();
		delegado.agregarConductor(conductor);
		restTemplate.postForEntity(REST_URI+"api/conductore/add", conductor1, Tmio1Conductore.class).getBody();
		delegado.agregarConductor(conductor1);
		
		when(restTemplate.getForObject(REST_URI+"api/conductore/findAll", Tmio1Conductore[].class)).thenReturn(lista);
		assertEquals(delegado.findAllConductore(),lista);
	}
	
}
