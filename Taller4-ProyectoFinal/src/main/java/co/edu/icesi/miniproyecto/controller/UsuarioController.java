package co.edu.icesi.miniproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
	
	@GetMapping("/login")
    public String login() {
        return "customlogin";
    }
	
	@GetMapping("/logout")
	public String logout() {
		return "customlogout";
	}
	
	
	

}
