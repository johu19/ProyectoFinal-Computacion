package co.edu.icesi.miniproyecto.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

public interface BusesRepository extends CrudRepository<Tmio1Bus, Integer> {
	
	List<Tmio1Bus> findByPlaca(String placa);

	
	
	
}
