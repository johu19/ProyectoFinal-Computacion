package co.edu.icesi.miniproyecto.daos;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import co.edu.icesi.miniproyecto.model.Dia;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

public interface ITmio1RutaDao {
	
	void save(Tmio1Ruta ruta);
	void update(Tmio1Ruta ruta);
	void delete(Tmio1Ruta ruta);
	Tmio1Ruta findById(Integer id);
	List<Tmio1Ruta> findByFechas(BigDecimal inicio, BigDecimal fin);
	List<Tmio1Ruta> findByHoras(BigDecimal inicio, BigDecimal fin);
	List<Tmio1Ruta> findAll();
	
	

}
