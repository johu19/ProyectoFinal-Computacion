package co.edu.icesi.miniproyecto.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.miniproyecto.clienteRest.BusClienteRest;
import co.edu.icesi.miniproyecto.clienteRest.ConductoreClienteRest;
import co.edu.icesi.miniproyecto.clienteRest.RutaClienteRest;
import co.edu.icesi.miniproyecto.clienteRest.ServicioClienteRest;
import co.edu.icesi.miniproyecto.exceptions.ServicioFechasException;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.services.BusService;
import co.edu.icesi.miniproyecto.services.ConductorService;
import co.edu.icesi.miniproyecto.services.RutaService;
import co.edu.icesi.miniproyecto.services.ServicioService;

@Controller
public class ServicioController {

//	private ServicioService servicioServ;

//	private RutaService rutaServ;

//	private BusService busServ;

//	private ConductorService condServ;
	
	
	@Autowired
	private ServicioClienteRest delegadoServicio;
	
	@Autowired
	private RutaClienteRest delegadoRuta;
	
	@Autowired
	private ConductoreClienteRest delegadoConductore;
	
	@Autowired
	private BusClienteRest delegadoBus;
	
	
	

//	@Autowired
//	public ServicioController(ServicioService s, RutaService r, BusService b, ConductorService c) {
//		servicioServ = s;
//		rutaServ = r;
//		busServ = b;
//		condServ = c;
//	}

	@GetMapping("/servs/")
	public String indexServicios(Model model) {
		model.addAttribute("servicios", delegadoServicio.findAllServicios());
		return "servs/index";
	}

	@GetMapping("servs/add")
	public String addServicio(Model model) {
		model.addAttribute("buses", delegadoBus.findAllBuses());
		model.addAttribute("conductores", delegadoConductore.findAllConductore());
		model.addAttribute("rutas", delegadoRuta.findAllRutas());
		model.addAttribute("servicio", new Tmio1Servicio());
		return "servs/add-serv";
	}
	
	
	@GetMapping("servs/filtrar")
	public String showFiltrarServicios(Model model) {
		model.addAttribute("servicio",new Tmio1Servicio());
		return "servs/filtrar-servs";
	}
	
	
	@PostMapping("servs/filtrar")
	public String filtrarServicios(@RequestParam(value = "action", required = true) String action, Model model,Tmio1Servicio s) {
		if(!action.equals("Cancelar")) {
			model.addAttribute("servicios", delegadoServicio.findByDate(s.getFechaServicio()));
			return "servs/index";
		}else {
			model.addAttribute("servicios",delegadoServicio.findAllServicios());
			return  "servs/index";
		}
	}
	
	
	@GetMapping("servs/limpiarfiltro")
	public String limpiarFiltro(Model model) {
		model.addAttribute("servicios", delegadoServicio.findAllServicios());
		return "servs/index";
	}
	
	

	@PostMapping("servs/add")
	public String saveServicio(@Valid @ModelAttribute("servicio") Tmio1Servicio servicio, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("rutas", delegadoRuta.findAllRutas());
				model.addAttribute("conductores", delegadoConductore.findAllConductore());
				model.addAttribute("buses", delegadoBus.findAllBuses());
				model.addAttribute("servicio", servicio);


				return "servs/add-serv";
			} else {
				try {

					Tmio1ServicioPK pk = new Tmio1ServicioPK();
					pk.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
					pk.setIdBus(servicio.getTmio1Bus().getId());
					pk.setIdRuta(servicio.getTmio1Ruta().getId());
					pk.setFechaInicio(servicio.getFechaServicio());
					pk.setFechaFin(new Date());
					servicio.setPlaneID(pk.getIdBus() + "_" + pk.getIdRuta() + "_" + pk.getCedulaConductor() + "_"
							+ servicio.getFechaServicio().toString());
					servicio.setId(pk);

					delegadoServicio.agregarServicio(servicio);
					
				} catch (Exception e) {
					
					if(e.getClass().equals(ServicioFechasException.class)) {
						return "servs/error-fechas";
					}

				}

			}
		return "redirect:/servs/";
	}

	@GetMapping("/servs/edit/{planeID}")
	public String showUpdateServicios(@PathVariable("planeID") String planeID, Model model) {

		Tmio1Servicio servicio = delegadoServicio.findByPlaneID(planeID);

		model.addAttribute("tmio1Servicio", servicio);

		model.addAttribute("buses", delegadoBus.findAllBuses());
		model.addAttribute("conductores", delegadoConductore.findAllConductore());
		model.addAttribute("rutas", delegadoRuta.findAllRutas());
		return "servs/update-serv";
	}

	@PostMapping("/servs/edit/{planeID}")
	public String updateServicio(@PathVariable("planeID") String id,
			@RequestParam(value = "action", required = true) String action, @Valid Tmio1Servicio tmio1Servicio,
			BindingResult bindingResult, Model model) {
		
		Tmio1Servicio servOrigi=delegadoServicio.findByPlaneID(id);
		
		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("servicio", tmio1Servicio);
				model.addAttribute("buses", delegadoBus.findAllBuses());
				model.addAttribute("conductores", delegadoConductore.findAllConductore());
				model.addAttribute("rutas", delegadoRuta.findAllRutas());
				return "servs/update-serv";
			} else
				try {
					
					delegadoServicio.actualizarServicio(tmio1Servicio);
					
				} catch (Exception e) {
					e.printStackTrace();
					if(e.getClass().equals(ServicioFechasException.class)) {
						
						try {
							delegadoServicio.agregarServicio(servOrigi);
						} catch (Exception e2) {
						}
						return "servs/error-fechas";
					}
				}
		}
		return "redirect:/servs/";
	}

	@GetMapping("/servs/del/{planeID}")
	public String deleteServicio(@PathVariable("planeID") String planeID) {
		delegadoServicio.borrarServicio(planeID);
		return "redirect:/servs/";
	}

}
