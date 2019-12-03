package co.edu.icesi.miniproyecto.pruebasTaller4;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.clienteRest.RutaClienteRest;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.servicioRest.RutaServicioRest;

public class RutaClienteRestTest {

	private RutaClienteRest delegado;
	
	@Mock
	private RutaServicioRest mock;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		delegado = new RutaClienteRest();
	}
	
	@Test
	public void testagregarRuta () {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId(0);
		ruta.setActiva("Y");
		ruta.setDescripcion("test");
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(82800));
		ruta.setHoraInicio(new BigDecimal(18000));
		ruta.setNumero("T31");
		when(mock.agregarRuta(ruta)).thenReturn(ruta);
		
	}

	@Test
	public void testfindAllRutas () {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId(0);
		ruta.setActiva("Y");
		ruta.setDescripcion("test");
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(82800));
		ruta.setHoraInicio(new BigDecimal(18000));
		ruta.setNumero("T31");
		
		Tmio1Ruta ruta1 = new Tmio1Ruta();
		ruta1.setId(1);
		ruta1.setActiva("Y");
		ruta1.setDescripcion("test");
		ruta1.setDiaFin(new BigDecimal(5));
		ruta1.setDiaInicio(new BigDecimal(1));
		ruta1.setHoraFin(new BigDecimal(82800));
		ruta1.setHoraInicio(new BigDecimal(18000));
		ruta1.setNumero("E21");
		
		List<Tmio1Ruta> lista = new ArrayList<Tmio1Ruta>();
		lista.add(ruta);
		lista.add(ruta1);
		
		when(mock.findAllRutas()).thenReturn(lista);
	}
	
}
