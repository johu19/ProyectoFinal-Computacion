package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.ConductoreClienteRest;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.servicioRest.ConductoreServicioRest;

public class ConductoreClienteRestTest {

	private ConductoreClienteRest delegado;
	
	@Mock
	private ConductoreServicioRest mock;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		delegado = new ConductoreClienteRest();
	}
	
	@Test
	public void testagregarConductor () throws ParseException {
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("1");
		conductor.setApellidos("a");
		conductor.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductor.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductor.setNombre("Arroyo");
		when(mock.agregarConductor(conductor)).thenReturn(conductor);
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
		
		List<Tmio1Conductore> lista = new ArrayList<Tmio1Conductore>();
		lista.add(conductor);
		lista.add(conductor1);
		
		when(mock.findAllConductores()).thenReturn(lista);
	}
	
}
