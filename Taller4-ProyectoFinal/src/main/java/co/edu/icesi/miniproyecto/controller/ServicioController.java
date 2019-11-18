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

import co.edu.icesi.miniproyecto.exceptions.ServicioFechasException;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.services.BusService;
import co.edu.icesi.miniproyecto.services.ConductorService;
import co.edu.icesi.miniproyecto.services.RutaService;
import co.edu.icesi.miniproyecto.services.ServicioService;

@Controller
public class ServicioController {

	private ServicioService servicioServ;

	private RutaService rutaServ;

	private BusService busServ;

	private ConductorService condServ;

	@Autowired
	public ServicioController(ServicioService s, RutaService r, BusService b, ConductorService c) {
		servicioServ = s;
		rutaServ = r;
		busServ = b;
		condServ = c;
	}

	@GetMapping("/servs/")
	public String indexServicios(Model model) {
		model.addAttribute("servicios", servicioServ.findAllServicios());
		return "servs/index";
	}

	@GetMapping("servs/add")
	public String addServicio(Model model) {
		model.addAttribute("buses", busServ.findAllBuses());
		model.addAttribute("conductores", condServ.findAllConductores());
		model.addAttribute("rutas", rutaServ.findAllRutas());
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
			model.addAttribute("servicios", servicioServ.findByDate(s.getFechaServicio()));
			return "servs/index";
		}else {
			model.addAttribute("servicios",servicioServ.findAllServicios());
			return  "servs/index";
		}
	}
	
	
	@GetMapping("servs/limpiarfiltro")
	public String limpiarFiltro(Model model) {
		model.addAttribute("servicios", servicioServ.findAllServicios());
		return "servs/index";
	}
	
	

	@PostMapping("servs/add")
	public String saveServicio(@Valid @ModelAttribute("servicio") Tmio1Servicio servicio, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("rutas", rutaServ.findAllRutas());
				model.addAttribute("conductores", condServ.findAllConductores());
				model.addAttribute("buses", busServ.findAllBuses());
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

					servicioServ.agregarServicio(servicio);
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

		Tmio1Servicio servicio = servicioServ.findByPlaneID(planeID);

		model.addAttribute("tmio1Servicio", servicio);

		model.addAttribute("buses", busServ.findAllBuses());
		model.addAttribute("conductores", condServ.findAllConductores());
		model.addAttribute("rutas", rutaServ.findAllRutas());
		return "servs/update-serv";
	}

	@PostMapping("/servs/edit/{planeID}")
	public String updateServicio(@PathVariable("planeID") String id,
			@RequestParam(value = "action", required = true) String action, @Valid Tmio1Servicio tmio1Servicio,
			BindingResult bindingResult, Model model) {
		
		Tmio1Servicio servOrigi=servicioServ.findByPlaneID(id);
		
		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("servicio", tmio1Servicio);
				model.addAttribute("buses", busServ.findAllBuses());
				model.addAttribute("conductores", condServ.findAllConductores());
				model.addAttribute("rutas", rutaServ.findAllRutas());
				return "servs/update-serv";
			} else
				try {
					Tmio1ServicioPK pk = new Tmio1ServicioPK();
					pk.setCedulaConductor(tmio1Servicio.getTmio1Conductore().getCedula());
					pk.setIdBus(tmio1Servicio.getTmio1Bus().getId());
					pk.setIdRuta(tmio1Servicio.getTmio1Ruta().getId());
					pk.setFechaInicio(new Date());
					pk.setFechaFin(tmio1Servicio.getFechaServicio());
					tmio1Servicio.setPlaneID(pk.getIdBus() + "_" + pk.getIdRuta() + "_" + pk.getCedulaConductor() + "_"
							+ tmio1Servicio.getFechaServicio().toString());
					
					tmio1Servicio.setId(servicioServ.findByPlaneID(id).getId());
					
					servicioServ.eliminarServicio(tmio1Servicio.getId());
					
					tmio1Servicio.setId(pk);
					servicioServ.agregarServicio(tmio1Servicio);
					
				} catch (Exception e) {
					if(e.getClass().equals(ServicioFechasException.class)) {
						
						try {
							servicioServ.agregarServicio(servOrigi);
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
		Tmio1ServicioPK pk = servicioServ.findByPlaneID(planeID).getId();
		servicioServ.eliminarServicio(pk);
		return "redirect:/servs/";
	}

}
