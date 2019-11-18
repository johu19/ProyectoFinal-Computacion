package co.edu.icesi.miniproyecto.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;


/**
 * The persistent class for the tmio1_rutas database table.
 * 
 */
@Entity
@Table(name="tmio1_rutas")
@NamedQuery(name="Tmio1Ruta.findAll", query="SELECT t FROM Tmio1Ruta t")
public class Tmio1Ruta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TMIO1_RUTAS_ID_GENERATOR", sequenceName="TMIO1_RUTAS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMIO1_RUTAS_ID_GENERATOR")
	private Integer id;

	@NotBlank(message="Digite el campo activa")
	private String activa;

	@NotBlank(message="Escriba la descripcion")
	private String descripcion;

//	@NotNull(message="Seleccione el dia de fin")
//	@Column(name="dia_fin")
//	private Dia diaFin;
//
//	@NotNull(message="Seleccione el dia de inicio")
//	@Column(name="dia_inicio")
//	private Dia diaInicio;
//
//	@DateTimeFormat(iso = ISO.TIME)
//	@NotNull(message="Digite la hora de fin")
//	@Column(name="hora_fin")
//	private LocalTime horaFin;
//
//	@DateTimeFormat(iso = ISO.TIME)
//	@NotNull(message="Digite la hora de inicio")
//	@Column(name="hora_inicio")
//	private LocalTime horaInicio;
	
	@NotNull(message = "Indique el dia de fin")
	@Positive(message="Debe ser un numero positivo")
	@Column(name="dia_fin")
	private BigDecimal diaFin;

	@NotNull(message = "Indique el dia de inicio")
	@Positive(message="Debe ser un numero positivo")
	@Column(name="dia_inicio")
	private BigDecimal diaInicio;

	@NotNull(message = "Indique la hora de fin")
	@Positive(message="Debe ser un numero positivo")
	@Column(name="hora_fin")
	private BigDecimal horaFin;

	@NotNull(message = "Indique la hora de inicio")
	@Positive(message="Debe ser un numero positivo")
	@Column(name="hora_inicio")
	private BigDecimal horaInicio;

	@NotBlank(message="Digite el numero de ruta")
	private String numero;

	//bi-directional many-to-one association to Tmio1Servicio
	@OneToMany(mappedBy="tmio1Ruta")
	private List<Tmio1Servicio> tmio1Servicios;

	//bi-directional many-to-one association to Tmio1ServiciosSitio
	@OneToMany(mappedBy="tmio1Ruta")
	private List<Tmio1ServiciosSitio> tmio1ServiciosSitios;

	//bi-directional many-to-one association to Tmio1SitiosRuta
	@OneToMany(mappedBy="tmio1Ruta1")
	private List<Tmio1SitiosRuta> tmio1SitiosRutas1;

	//bi-directional many-to-one association to Tmio1SitiosRuta
	@OneToMany(mappedBy="tmio1Ruta2")
	private List<Tmio1SitiosRuta> tmio1SitiosRutas2;

	public Tmio1Ruta() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActiva() {
		return this.activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
//	public Dia getDiaFin() {
//		return this.diaFin;
//	}
//
//	public void setDiaFin(Dia diaFin) {
//		this.diaFin = diaFin;
//	}
//
//	public Dia getDiaInicio() {
//		return this.diaInicio;
//	}
//
//	public void setDiaInicio(Dia diaInicio) {
//		this.diaInicio = diaInicio;
//	}
//
//	public LocalTime getHoraFin() {
//		return this.horaFin;
//	}
//
//	public void setHoraFin(LocalTime horaFin) {
//		this.horaFin = horaFin;
//	}
//
//	public LocalTime getHoraInicio() {
//		return this.horaInicio;
//	}
//
//	public void setHoraInicio(LocalTime horaInicio) {
//		this.horaInicio = horaInicio;
//	}

	public BigDecimal getDiaFin() {
		return diaFin;
	}

	public void setDiaFin(BigDecimal diaFin) {
		this.diaFin = diaFin;
	}

	public BigDecimal getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(BigDecimal diaInicio) {
		this.diaInicio = diaInicio;
	}

	public BigDecimal getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(BigDecimal horaFin) {
		this.horaFin = horaFin;
	}

	public BigDecimal getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(BigDecimal horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Tmio1Servicio> getTmio1Servicios() {
		return this.tmio1Servicios;
	}

	public void setTmio1Servicios(List<Tmio1Servicio> tmio1Servicios) {
		this.tmio1Servicios = tmio1Servicios;
	}

	public Tmio1Servicio addTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().add(tmio1Servicio);
		tmio1Servicio.setTmio1Ruta(this);

		return tmio1Servicio;
	}

	public Tmio1Servicio removeTmio1Servicio(Tmio1Servicio tmio1Servicio) {
		getTmio1Servicios().remove(tmio1Servicio);
		tmio1Servicio.setTmio1Ruta(null);

		return tmio1Servicio;
	}

	public List<Tmio1ServiciosSitio> getTmio1ServiciosSitios() {
		return this.tmio1ServiciosSitios;
	}

	public void setTmio1ServiciosSitios(List<Tmio1ServiciosSitio> tmio1ServiciosSitios) {
		this.tmio1ServiciosSitios = tmio1ServiciosSitios;
	}

	public Tmio1ServiciosSitio addTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().add(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Ruta(this);

		return tmio1ServiciosSitio;
	}

	public Tmio1ServiciosSitio removeTmio1ServiciosSitio(Tmio1ServiciosSitio tmio1ServiciosSitio) {
		getTmio1ServiciosSitios().remove(tmio1ServiciosSitio);
		tmio1ServiciosSitio.setTmio1Ruta(null);

		return tmio1ServiciosSitio;
	}

	public List<Tmio1SitiosRuta> getTmio1SitiosRutas1() {
		return this.tmio1SitiosRutas1;
	}

	public void setTmio1SitiosRutas1(List<Tmio1SitiosRuta> tmio1SitiosRutas1) {
		this.tmio1SitiosRutas1 = tmio1SitiosRutas1;
	}

	public Tmio1SitiosRuta addTmio1SitiosRutas1(Tmio1SitiosRuta tmio1SitiosRutas1) {
		getTmio1SitiosRutas1().add(tmio1SitiosRutas1);
		tmio1SitiosRutas1.setTmio1Ruta1(this);

		return tmio1SitiosRutas1;
	}

	public Tmio1SitiosRuta removeTmio1SitiosRutas1(Tmio1SitiosRuta tmio1SitiosRutas1) {
		getTmio1SitiosRutas1().remove(tmio1SitiosRutas1);
		tmio1SitiosRutas1.setTmio1Ruta1(null);

		return tmio1SitiosRutas1;
	}

	public List<Tmio1SitiosRuta> getTmio1SitiosRutas2() {
		return this.tmio1SitiosRutas2;
	}

	public void setTmio1SitiosRutas2(List<Tmio1SitiosRuta> tmio1SitiosRutas2) {
		this.tmio1SitiosRutas2 = tmio1SitiosRutas2;
	}

	public Tmio1SitiosRuta addTmio1SitiosRutas2(Tmio1SitiosRuta tmio1SitiosRutas2) {
		getTmio1SitiosRutas2().add(tmio1SitiosRutas2);
		tmio1SitiosRutas2.setTmio1Ruta2(this);

		return tmio1SitiosRutas2;
	}

	public Tmio1SitiosRuta removeTmio1SitiosRutas2(Tmio1SitiosRuta tmio1SitiosRutas2) {
		getTmio1SitiosRutas2().remove(tmio1SitiosRutas2);
		tmio1SitiosRutas2.setTmio1Ruta2(null);

		return tmio1SitiosRutas2;
	}

}