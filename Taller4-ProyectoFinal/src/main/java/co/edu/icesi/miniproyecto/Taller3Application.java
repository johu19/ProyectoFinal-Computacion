package co.edu.icesi.miniproyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.miniproyecto.clienteRest.UsuarioClienteRest;
import co.edu.icesi.miniproyecto.model.TipoUsuario;
import co.edu.icesi.miniproyecto.model.Usuario;
import co.edu.icesi.miniproyecto.repositories.BusesRepository;
import co.edu.icesi.miniproyecto.repositories.ConductoresRepository;
import co.edu.icesi.miniproyecto.repositories.RutasRepository;
import co.edu.icesi.miniproyecto.repositories.UsuariosRepository;

@SpringBootApplication
public class Taller3Application {
	
	

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(Taller3Application.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(UsuariosRepository repos, RutasRepository reposRuta, BusesRepository reposBus, ConductoresRepository reposCond,
			UsuarioClienteRest usuRest) {
		
		return (args) -> {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin");
			u1.setPassword(passwordEncoder.encode("123"));
			u1.setTipo(TipoUsuario.Administrador);
			repos.save(u1);
//			usuRest.agregarUsuario(u1);
			
			Usuario u2 = new Usuario();
			u2.setUsername("ope");
			u2.setPassword(passwordEncoder.encode("123"));
			u2.setTipo(TipoUsuario.Operador);
			repos.save(u2);
//			usuRest.agregarUsuario(u2);
			
			
		};
	}

}
