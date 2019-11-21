package co.edu.icesi.miniproyecto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.miniproyecto.clienteRest.BusClienteRest;
import co.edu.icesi.miniproyecto.exceptions.BusPlacaRepetidaException;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.services.BusService;

@Controller
public class BusController {

	private BusService busServ;
	
//	private BusClienteRest delegado;

	@Autowired
	public BusController(BusService b, BusClienteRest d) {
		busServ = b;
//		delegado = d;
	}

	@GetMapping("/buses/")
	public String indexBuses(Model model) {
		model.addAttribute("buses", busServ.findAllBuses());
		return "buses/index";
	}

	@GetMapping("buses/add")
	public String addBuses(Model model) {
		Tmio1Bus bu = new Tmio1Bus();
		model.addAttribute("bus", bu);
		model.addAttribute("tipos", busServ.obtenerTipos());
		return "buses/add-bus";
	}

	@PostMapping("buses/add")
	public String saveBus(@Valid @ModelAttribute("bus") Tmio1Bus bus, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {

		if (!action.equals("Cancelar"))
			if (bindingResult.hasErrors()) {
				model.addAttribute("bus", bus);
				model.addAttribute("tipos", busServ.obtenerTipos());
				return "buses/add-bus";
			} else {
				try {

					busServ.agregarBus(bus);
//					delegado.agregarBus(bus);
				} catch (Exception e) {
					if (e.getClass().equals(BusPlacaRepetidaException.class)) {
						return "buses/error-placa";
					}
				}

			}
		return "redirect:/buses/";
	}

}
