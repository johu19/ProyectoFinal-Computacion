package co.edu.icesi.miniproyecto.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
@Entity
@Table(name="usuarios")
@Data
public class Usuario {
	
	private TipoUsuario type;
	
	@Id
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;

	public TipoUsuario getTipo() {
		return type;
	}

	public void setTipo(TipoUsuario tipo) {
		this.type = tipo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
