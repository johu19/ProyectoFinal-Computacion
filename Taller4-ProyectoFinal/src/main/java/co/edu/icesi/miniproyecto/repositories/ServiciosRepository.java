package co.edu.icesi.miniproyecto.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

public interface ServiciosRepository extends CrudRepository<Tmio1Servicio, Tmio1ServicioPK> {
	
	Optional<Tmio1Servicio> findByPlaneID(String planeID);
	
	List<Tmio1Servicio> findByFechaServicio(Date fechaServicio);

}
