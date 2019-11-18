package co.edu.icesi.miniproyecto.services;

import java.time.LocalDate;
import java.util.Date;

import co.edu.icesi.miniproyecto.exceptions.ConductorCedulaRepetidaException;
import co.edu.icesi.miniproyecto.exceptions.ConductorFechasException;
import co.edu.icesi.miniproyecto.exceptions.ConductorNullException;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

public interface IConductorService {

public Tmio1Conductore agregarConductor(Tmio1Conductore conductor) throws ConductorNullException, ConductorFechasException, ConductorCedulaRepetidaException ;
	
	public Tmio1Conductore eliminarConductor(String cedula);
	
	public Tmio1Conductore consultarConductor(String cedula);
	
	public Tmio1Conductore actualizarConductor(String cedula, String apellidos, String nombre, Date fechaContratacion, Date fechaNacimiento );
	
}
