package proj.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proj.model.Usuario;
import proj.repository.UsuarioRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	UsuarioRepository usuarioRepositorio;

	
	@GetMapping
	public String mostraPaginaInicial(Model model, Principal principal) {
		
		Usuario u = null;
		
		if (principal != null)
    	{
    		u = usuarioRepositorio.findByEmail(principal.getName());
    	}
		
		model.addAttribute("principal", u);

		return "index";
	}

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("erro")
    public String erro() {
        return "erro";
    }

    @GetMapping("cadastro")
    public String suco() {
        return "cadastro_usuario";
    }
}
