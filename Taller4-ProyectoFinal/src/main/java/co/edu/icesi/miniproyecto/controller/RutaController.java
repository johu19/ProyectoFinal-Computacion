package co.edu.icesi.miniproyecto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.miniproyecto.exceptions.RutaDiasException;
import co.edu.icesi.miniproyecto.exceptions.RutaHorasException;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.services.RutaService;

@Controller
public class RutaController {
	
	private RutaService rutaServ;
	
	@Autowired
	public RutaController(RutaService r) {
		rutaServ = r;
	}
	
	
	@GetMapping("/rutas/")
	public String indexRuta(Model model) {
		model.addAttribute("rutas",rutaServ.findAllRutas());
		return "rutas/index";
	}
	
	
	@GetMapping("rutas/add")
	public String addRuta(Model model) {
		model.addAttribute("ruta", new Tmio1Ruta());
		return "rutas/add-ruta";
	}
	
	@PostMapping("rutas/add")
	public String saveRuta(@Valid @ModelAttribute("ruta") Tmio1Ruta ruta, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("ruta",ruta);
				return "rutas/add-ruta";
			} else {
				try {
				
					rutaServ.agregarRuta(ruta);
				} catch (Exception e) {
					if(e.getClass().equals(RutaHorasException.class)) {
						return "rutas/error-horas";
					}else if(e.getClass().equals(RutaDiasException.class)) {
						return "rutas/error-dias";
					}
				}
				
			}
		return "redirect:/rutas/";
	}

}
