package co.edu.icesi.miniproyecto.controller;

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

import co.edu.icesi.miniproyecto.clienteRest.RutaClienteRest;
import co.edu.icesi.miniproyecto.clienteRest.SitioClienteRest;
import co.edu.icesi.miniproyecto.clienteRest.SitiosRutaClienteRest;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;
import co.edu.icesi.miniproyecto.services.RutaService;
import co.edu.icesi.miniproyecto.services.SitioService;
import co.edu.icesi.miniproyecto.services.SitiosRutaService;

@Controller
public class SitiosRutaController {
	
	
//	@Autowired
//	private SitiosRutaService srServ;
//	
//	@Autowired
//	private RutaService rutaServ;
//	
//	@Autowired
//	private SitioService sitioServ;
	
	@Autowired
	private SitiosRutaClienteRest delegadoSR;
	
	@Autowired
	private SitioClienteRest delegadoSitio;
	
	@Autowired
	private RutaClienteRest delegadoRuta;
	
	
	@GetMapping("/srs/")
	public String indexSitiosRuta(Model model) {
		model.addAttribute("srs", delegadoSR.findAllSitiosRuta());
		return "sitiosRuta/index";
	}
	
	
	@GetMapping("srs/add")
	public String addSitiosRuta(Model model) {
		model.addAttribute("sitios", delegadoSitio.findAllSitios());
		model.addAttribute("rutas",delegadoRuta.findAllRutas());
		model.addAttribute("sr", new Tmio1SitiosRuta());
		return "sitiosRuta/add-sitiosRuta";
	}
	
	
	
	@PostMapping("srs/add")
	public String saveSitiosRuta(@Valid @ModelAttribute("sr") Tmio1SitiosRuta sr, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("rutas", delegadoRuta.findAllRutas());
				model.addAttribute("sitios", delegadoSitio.findAllSitios());
				model.addAttribute("sr", sr);
				

				return "sitiosRuta/add-sitiosRuta";
			} else {
				try {

					Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();
					pk.setIdRuta(sr.getTmio1Ruta1().getId());
					pk.setIdSitio(sr.getTmio1Sitio1().getId());
					sr.setPlaneID(pk.getIdRuta()+"_"+pk.getIdSitio());
					sr.setId(pk);
					delegadoSR.agregarSitiosRuta(sr);
					
				} catch (Exception e) {
					
					e.printStackTrace();

				}

			}
		return "redirect:/srs/";
	}
	
	
	@GetMapping("/srs/edit/{planeID}")
	public String showUpdateServicios(@PathVariable("planeID") String planeID, Model model) {

		Tmio1SitiosRuta sr = delegadoSR.findByPlanedId(planeID);
		
		model.addAttribute("sr", sr);
		model.addAttribute("sitios", delegadoSitio.findAllSitios());
		model.addAttribute("rutas", delegadoRuta.findAllRutas());
		return "servs/update-sitiosRuta";
	}
	
	
	@PostMapping("/srs/edit/{planeID}")
	public String updateSitiosRuta(@Valid @ModelAttribute("sr") Tmio1SitiosRuta sr, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (!action.equals("Cancelar"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("rutas", delegadoRuta.findAllRutas());
				model.addAttribute("sitios", delegadoSitio.findAllSitios());
				model.addAttribute("sr", sr);


				return "sitiosRuta/update-sitiosRuta";
			} else {
				try {

					delegadoSR.actualizarSitiosRuta(sr);
					
				} catch (Exception e) {
					
					e.printStackTrace();

				}

			}
		return "redirect:/srs/";
	}
	
	
	
	
	
	@GetMapping("/srs/del{planeID}")
	public String deleteSitiosRuta(@PathVariable("planeID") String planeID) {
		Tmio1SitiosRutaPK pk = delegadoSR.findByPlanedId(planeID).getId();
		delegadoSR.borrarSitiosRuta(pk);
		return "redirect:/srs/";
	}
	
	
	
	
	
	

}
