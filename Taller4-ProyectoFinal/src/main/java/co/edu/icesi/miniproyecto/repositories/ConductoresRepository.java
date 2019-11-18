package co.edu.icesi.miniproyecto.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

public interface ConductoresRepository extends CrudRepository<Tmio1Conductore, String> {
	
	List<Tmio1Conductore> findByCedula(String cedula);

}
