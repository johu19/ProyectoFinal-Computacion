package co.edu.icesi.miniproyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the tmio1_sitios_rutas database table.
 * 
 */
@Entity
@Table(name="tmio1_sitios_rutas")
@NamedQuery(name="Tmio1SitiosRuta.findAll", query="SELECT t FROM Tmio1SitiosRuta t")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tmio1SitiosRuta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Tmio1SitiosRutaPK id;

	//bi-directional many-to-one association to Tmio1Ruta
	@ManyToOne
	@NotNull(message="Seleccione la ruta")
	@JoinColumn(name="id_ruta", insertable=false, updatable=false)
	private Tmio1Ruta tmio1Ruta;

	//bi-directional many-to-one association to Tmio1Sitio
	@ManyToOne
//	@NotNull(message="Seleccione el sitio")
	@JoinColumn(name="id_sitio", insertable=false, updatable=false)
	private Tmio1Sitio tmio1Sitio;


	public Tmio1SitiosRuta() {
	}
	
	@Column(name="plane_id")
	private String planeID;
	
	
	@Column(name="sitio_id")
	private Integer sitioID;
	
	
	public Integer getSitioID() {
		return sitioID;
	}
	public void setSitioID(Integer sitioID) {
		this.sitioID = sitioID;
	}
	public String getPlaneID() {
		return planeID;
	}
	public void setPlaneID(String planeID) {
		this.planeID = planeID;
	}

	public Tmio1SitiosRutaPK getId() {
		return this.id;
	}

	public void setId(Tmio1SitiosRutaPK id) {
		this.id = id;
	}

	public Tmio1Ruta getTmio1Ruta() {
		return this.tmio1Ruta;
	}

	public void setTmio1Ruta(Tmio1Ruta tmio1Ruta1) {
		this.tmio1Ruta = tmio1Ruta1;
	}

	public Tmio1Sitio getTmio1Sitio() {
		return this.tmio1Sitio;
	}

	public void setTmio1Sitio(Tmio1Sitio tmio1Sitio1) {
		this.tmio1Sitio = tmio1Sitio1;
	}

}