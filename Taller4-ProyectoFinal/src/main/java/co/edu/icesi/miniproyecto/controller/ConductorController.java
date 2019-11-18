package co.edu.icesi.miniproyecto.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.miniproyecto.exceptions.ConductorCedulaRepetidaException;
import co.edu.icesi.miniproyecto.exceptions.ConductorFechasException;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.services.ConductorService;

@Controller
public class ConductorController {
	
	private ConductorService condServ;
	
	
	@Autowired
	public ConductorController(ConductorService c) {
		condServ=c;
	}
	
	
	@GetMapping("/conductores/")
	public String indexCond(Model model) {
		model.addAttribute("conductores",condServ.findAllConductores());
		return "conductores/index";
	}
	
	
	@GetMapping("conductores/add")
	public String addConductores(Model model) {
		Tmio1Conductore conductor = new Tmio1Conductore();
		model.addAttribute("conductor", conductor);
		return "conductores/add-conductor";
	}
	
	@PostMapping("conductores/add")
	public String saveConductor(@Valid @ModelAttribute("conductor") Tmio1Conductore conductor, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		
		if (!action.equals("Cancelar"))
			if (bindingResult.hasErrors()) {
				
				model.addAttribute("conductor",conductor);
				return "conductores/add-conductor";
			} else {
				try {
				
					condServ.agregarConductor(conductor);
				} catch (Exception e) {
					if(e.getClass().equals(ConductorFechasException.class)) {
						return "conductores/error-fechas";
					}else if(e.getClass().equals(ConductorCedulaRepetidaException.class)) {
						return "conductores/error-cedula";
					}
				}
				
			}
		
		return "redirect:/conductores/";
	}

}
