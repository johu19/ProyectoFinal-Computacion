package co.edu.icesi.miniproyecto.services;

import java.math.BigDecimal;
import java.time.LocalTime;

import co.edu.icesi.miniproyecto.exceptions.RutaDiasException;
import co.edu.icesi.miniproyecto.exceptions.RutaHorasException;
import co.edu.icesi.miniproyecto.exceptions.RutaIDRepetidoException;
import co.edu.icesi.miniproyecto.exceptions.RutaNullException;
import co.edu.icesi.miniproyecto.model.Dia;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

public interface IRutaService {
	
	public Tmio1Ruta agregarRuta(Tmio1Ruta ruta) throws RutaNullException, RutaDiasException, RutaHorasException, RutaIDRepetidoException;

	public Tmio1Ruta eliminarRuta(Integer id);

	public Tmio1Ruta consultarRuta(Integer id);

	public Tmio1Ruta actualizarRuta(Integer id, String activa, String descripcion, BigDecimal diaInicio,
			BigDecimal diaFin, BigDecimal horaInicio, BigDecimal horaFin);

}
