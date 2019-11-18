package co.edu.icesi.miniproyecto.daos;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

public interface ITmio1BusDao {
	
	List<Tmio1Bus> findByPlaca(String placa);
	List<Tmio1Bus> findByMarca(String marca);
	List<Tmio1Bus> findByModelo(BigDecimal modelo);
	List<Tmio1Bus> findAll();
	void save(Tmio1Bus bus);
	Tmio1Bus findById(Integer id);
	void update(Tmio1Bus bus);
	void delete(Tmio1Bus bus);

}
