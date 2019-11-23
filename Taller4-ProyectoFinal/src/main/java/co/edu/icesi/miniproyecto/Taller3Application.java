package co.edu.icesi.miniproyecto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.miniproyecto.clienteRest.UsuarioClienteRest;
import co.edu.icesi.miniproyecto.daos.Tmio1RutaDao;
import co.edu.icesi.miniproyecto.daos.Tmio1SitioDao;
import co.edu.icesi.miniproyecto.daos.UsuarioDao;
import co.edu.icesi.miniproyecto.model.TipoBus;
import co.edu.icesi.miniproyecto.model.TipoUsuario;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.Usuario;
import co.edu.icesi.miniproyecto.repositories.BusesRepository;
import co.edu.icesi.miniproyecto.repositories.ConductoresRepository;
import co.edu.icesi.miniproyecto.repositories.RutasRepository;
import co.edu.icesi.miniproyecto.repositories.UsuariosRepository;
import co.edu.icesi.miniproyecto.services.BusService;
import co.edu.icesi.miniproyecto.services.ConductorService;
import co.edu.icesi.miniproyecto.services.RutaService;
import co.edu.icesi.miniproyecto.services.SitioService;
import co.edu.icesi.miniproyecto.services.UsuarioService;

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
	public CommandLineRunner demo(UsuarioService usuServ,	UsuarioClienteRest usuRest,
			RutaService rutaServ, SitioService sitioServ, ConductorService condServ, BusService busServ) {
		
		return (args) -> {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin");
			u1.setPassword(passwordEncoder.encode("123"));
			u1.setTipo(TipoUsuario.Administrador);
			usuServ.agregarUsuario(u1);
//			usuRest.agregarUsuario(u1);
			
			Usuario u2 = new Usuario();
			u2.setUsername("ope");
			u2.setPassword(passwordEncoder.encode("123"));
			u2.setTipo(TipoUsuario.Operador);
			usuServ.agregarUsuario(u2);
//			usuRest.agregarUsuario(u2);
			
			Tmio1Sitio sitio = new Tmio1Sitio();
			sitio.setNombre("Universidades");
			sitio.setDescripcion("Al lado de jardin plaza");
			sitioServ.agregarSitio(sitio);
			
			Tmio1Sitio sitio1 = new Tmio1Sitio();
			sitio1.setNombre("Capri");
			sitio1.setDescripcion("Al lado de san sur");
			sitioServ.agregarSitio(sitio1);
			
			Tmio1Bus bus = new Tmio1Bus();
			bus.setCapacidad(new BigDecimal(10));
			bus.setMarca("Audi");
			bus.setModelo(new BigDecimal(2018));
			bus.setPlaca("CWR897");
			bus.setTipo(TipoBus.A);
			busServ.agregarBus(bus);
			
			Tmio1Bus bus1 = new Tmio1Bus();
			bus1.setCapacidad(new BigDecimal(25));
			bus1.setMarca("Volvo");
			bus1.setModelo(new BigDecimal(2020));
			bus1.setPlaca("UBU335");
			bus1.setTipo(TipoBus.T);
			busServ.agregarBus(bus1);
			
			
			
			Tmio1Ruta ruta = new Tmio1Ruta();
			ruta.setActiva("SI");
			ruta.setDescripcion("E21");
			ruta.setDiaInicio(new BigDecimal(2));
			ruta.setDiaFin(new BigDecimal(6));
			ruta.setHoraInicio(new BigDecimal(5));
			ruta.setHoraFin(new BigDecimal(21));
			ruta.setNumero("43");
			rutaServ.agregarRuta(ruta);
			
			
			Tmio1Ruta ruta1 = new Tmio1Ruta();
			ruta1.setActiva("SI");
			ruta1.setDescripcion("A11");
			ruta1.setDiaInicio(new BigDecimal(1));
			ruta1.setDiaFin(new BigDecimal(7));
			ruta1.setHoraInicio(new BigDecimal(4));
			ruta1.setHoraFin(new BigDecimal(22));
			ruta1.setNumero("31");
			rutaServ.agregarRuta(ruta1);
			
			
			Tmio1Conductore cond = new Tmio1Conductore();
			cond.setApellidos("Galvis");
			cond.setCedula("1151963652");
			cond.setNombre("Jose");
			cond.setFechaNacimiento(new Date(10));
			cond.setFechaContratacion(new Date(1000000));
			condServ.agregarConductor(cond);
			
			
			
		};
	}

}
