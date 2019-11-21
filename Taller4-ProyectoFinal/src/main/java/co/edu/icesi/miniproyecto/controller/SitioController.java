package co.edu.icesi.miniproyecto.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.miniproyecto.clienteRest.SitioClienteRest;
import co.edu.icesi.miniproyecto.exceptions.ServicioFechasException;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.services.SitioService;

@Controller
public class SitioController {
	
	@Autowired
	private SitioService serv;
	
//	@Autowired
//	private SitioClienteRest delegado;
	
	@GetMapping("/sitios/")
	public String indexSitios(Model model) {
		model.addAttribute("sitios", serv.findAllSitios());
		return "sitios/index";
	}

	@GetMapping("sitios/add")
	public String addSitio(Model model) {
		model.addAttribute("sitio", new Tmio1Sitio());
		return "sitios/add-sitio";
	}
	
	
	@PostMapping("sitios/add")
	public String saveSitio(@Valid @ModelAttribute("sitio") Tmio1Sitio sitio, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar"))
			if (bindingResult.hasErrors()) {
				
				model.addAttribute("sitio",sitio);

				return "sitios/add-sitio";
			} else {
				try {
					serv.agregarSitio(sitio);
//					delegado.agregarSitio(sitio);
						
				} catch (Exception e) {
					
					e.printStackTrace();

				}

			}
		return "redirect:/sitios/";
	}
	
	@GetMapping("/sitios/del/{id}")
	public String deleteSitio(@PathVariable("id") Integer id) {
//		delegado.borrarSitio(id);
		serv.eliminarSitio(id);
		return "redirect:/sitios/";
	}
	
	
	@GetMapping("/sitios/edit/{id}")
	public String  showUpdateSitio(@PathVariable("id") Integer id, Model model) {
		Tmio1Sitio sitio = serv.consultarSitio(id);
		model.addAttribute("sitio", sitio);
		return "sitios/update-sitio";
	}
	
	
	
	@PostMapping("/sitios/edit/{id}")
	public String updateSitio(@PathVariable("id") Integer id,
			@RequestParam(value="action",required = true) String action, @Valid Tmio1Sitio sitio,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("sitio", sitio);
				
				return "sitios/update-serv";
			} else
				try {
					serv.actualizarSitio(sitio.getId(), sitio.getNombre(), sitio.getDescripcion());
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}

		}
		return "redirect:/sitios/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
