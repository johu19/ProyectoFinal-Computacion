package co.edu.icesi.miniproyecto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the tmio1_servicios database table.
 * 
 */
@Entity
@Table(name="tmio1_servicios")
@NamedQuery(name="Tmio1Servicio.findAll", query="SELECT t FROM Tmio1Servicio t")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tmio1Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Tmio1ServicioPK id;

	@NotNull(message="Seleccione el bus")
	@ManyToOne
	@JoinColumn(name="id_bus", insertable=false, updatable=false)
	@JsonIgnore
	private Tmio1Bus tmio1Bus;

	//bi-directional many-to-one association to Tmio1Conductore
	@NotNull(message="Seleccione el conductor")
	@ManyToOne
	@JoinColumn(name="cedula_conductor", insertable=false, updatable=false)
	@JsonIgnore
	private Tmio1Conductore tmio1Conductore;

	//bi-directional many-to-one association to Tmio1Ruta
	@NotNull(message="Seleccione la ruta")
	@ManyToOne
	@JoinColumn(name="id_ruta", insertable=false, updatable=false)
	@JsonIgnore
	private Tmio1Ruta tmio1Ruta;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Seleccione la fecha del servicio")
	private Date fechaServicio;
	
	
	@Column(name="plane_id")
	private String planeID;
	
	
	public String getPlaneID() {
		return planeID;
	}
	public void setPlaneID(String planeID) {
		this.planeID = planeID;
	}

	public Tmio1Servicio() {
	}

	public Tmio1ServicioPK getId() {
		return this.id;
	}

	public void setId(Tmio1ServicioPK id) {
		this.id = id;
	}

	@JsonIgnore
	public Tmio1Bus getTmio1Bus() {
		return this.tmio1Bus;
	}

	@JsonIgnore
	public void setTmio1Bus(Tmio1Bus tmio1Bus) {
		this.tmio1Bus = tmio1Bus;
	}

	@JsonIgnore
	public Tmio1Conductore getTmio1Conductore() {
		return this.tmio1Conductore;
	}

	@JsonIgnore
	public void setTmio1Conductore(Tmio1Conductore tmio1Conductore) {
		this.tmio1Conductore = tmio1Conductore;
	}

	@JsonIgnore
	public Tmio1Ruta getTmio1Ruta() {
		return this.tmio1Ruta;
	}

	@JsonIgnore
	public void setTmio1Ruta(Tmio1Ruta tmio1Ruta) {
		this.tmio1Ruta = tmio1Ruta;
	}

	public Date getFechaServicio() {
		return fechaServicio;
	}

	public void setFechaServicio(Date fechaServicio) {
		this.fechaServicio = fechaServicio;
	}

}