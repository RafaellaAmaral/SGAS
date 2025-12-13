package proj.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import jakarta.servlet.http.HttpServletRequest;
import proj.exception.BadRequestException;
import proj.model.Animal;
import proj.model.CandidaturaServico;
import proj.model.Favoritos;
import proj.model.Servico;
import proj.model.SolicitacaoAdocao;

import proj.model.Animal;
import proj.model.SolicitacaoAdocao;
import proj.model.SolicitacaoAdocaoKey;

import proj.model.Usuario;
import proj.repository.AnimalRepository;
import proj.repository.UsuarioRepository;
import proj.service.AnimalService;
import proj.service.CandidaturaServicoService;
import proj.service.FavoritosService;
import proj.service.ServicoService;
import proj.service.SolicitacaoAdocaoService;
import proj.service.UsuarioService;


@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	UsuarioRepository usuarioRepositorio;
	
	@Autowired
	AnimalService animalService;
	
	@Autowired
	ServicoService servicoService;
	
	@Autowired
	FavoritosService favoritosService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	SolicitacaoAdocaoService solicitacaoAdocaoService;
	
	@Autowired
	CandidaturaServicoService candidaturaServicoService;


    
    @GetMapping
    public String mostraPaginaInicial(Model model, Principal principal) {
        Usuario u = null;
        if (principal != null) {
            u = usuarioRepositorio.findByEmail(principal.getName());
        }
        model.addAttribute("principal", u);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/erro")
    public String erro() {
        return "erro";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro_usuario";
    }
    
    @GetMapping("doacao")
    public String mostraDoacao(Model model, Principal principal) {
    	
    	Usuario u = null;
		
		if (principal != null)
    	{
    		u = usuarioRepositorio.findByEmail(principal.getName());
    	}
		
		model.addAttribute("principal", u);
		
		return "doacao";
    	
    }
    
    @GetMapping("queroadotar")
    public String mostraAnimais(Model model, Principal principal) {
    	
    	Usuario u = null;
    	Set<Long> animaisFavoritos = new HashSet<>();
    	Set<Long> solicitacoesFeitas = new HashSet<>();
		
		if (principal != null)
    	{
    		u = usuarioRepositorio.findByEmail(principal.getName());
    		 animaisFavoritos = favoritosService.listarFavoritosPorUsuario(u.getId())
    	                .stream()
    	                .map(f -> f.getAnimal().getId())
    	                .collect(Collectors.toSet());
    		 
    		 solicitacoesFeitas = solicitacaoAdocaoService.listarSolicitacoesPorUsuario(u.getId())
 	                .stream()
 	                .map(f -> f.getAnimal().getId())
 	                .collect(Collectors.toSet());
    	        
    	        
    	}
		
		model.addAttribute("principal", u);
		model.addAttribute("animaisFavoritos", animaisFavoritos);
		model.addAttribute("solicitacoesFeitas", solicitacoesFeitas);
		
		List<Animal> listaAnimais = animalService.listarTodos();
        model.addAttribute("listaAnimais", listaAnimais);
		
		return "quero-adotar2";
    	
    }
    
    @PostMapping("/favoritar/{usuario_id}/{animal_id}")
    public String favoritarAnimal(@PathVariable Long usuario_id, @PathVariable Long animal_id) {
    	
    	Usuario u = usuarioService.getById(usuario_id);
        Animal a = animalService.buscarPeloId(animal_id);
        

        Favoritos favorito = new Favoritos(a, u);
        favoritosService.inserir(favorito);
        
       

        return "redirect:/queroadotar";
    }
    
    @PostMapping("/adotar/{usuario_id}/{animal_id}")
    public String solicitarAdocao(@PathVariable Long usuario_id, @PathVariable Long animal_id,
    		Model model, Principal principal, HttpServletRequest request) {
    	
    	Usuario u = usuarioService.getById(usuario_id);
        Animal a = animalService.buscarPeloId(animal_id);
        
        Usuario p = null;

        if (principal != null) {
            p = usuarioRepositorio.findByEmail(principal.getName());
            
            model.addAttribute("principal", p);

            if (p == null) {
                try {
                    request.logout();
                } catch (Exception e) {
                }
            }
        }

        if (u == null) {
            throw new BadRequestException("Usuário precisa estar logado.");
        }

        SolicitacaoAdocao s = new SolicitacaoAdocao(a, u, "pendente", LocalDate.now());
        solicitacaoAdocaoService.inserir(s);

        return "redirect:/queroadotar";
    }
    
    @GetMapping("querovoluntariar")
    public String mostraServicos(Model model, Principal principal) {
    	
    	Usuario u = null;
    	Set<Long> candidaturasFeitas = new HashSet<>();
		
		if (principal != null)
    	{
    		u = usuarioRepositorio.findByEmail(principal.getName());
    		 
    		 candidaturasFeitas = candidaturaServicoService.listarCandidaturasPorUsuario(u.getId())
 	                .stream()
 	                .map(f -> f.getServico().getId())
 	                .collect(Collectors.toSet());
    	        
    	        
    	}
		
		model.addAttribute("principal", u);
		model.addAttribute("candidaturasFeitas", candidaturasFeitas);
		
		List<Servico> listaServicos = servicoService.listarTodos();
        model.addAttribute("listaServicos", listaServicos);
		
		return "quero-voluntariar";
    	
    }
    
    @PostMapping("/candidatar/{usuario_id}/{servico_id}")
    public String CandidatarEmServico(@PathVariable Long usuario_id, @PathVariable Long servico_id,
    		Model model, Principal principal, HttpServletRequest request) {
    	
    	Usuario u = usuarioService.getById(usuario_id);
        Servico s = servicoService.buscarPeloId(servico_id);
        
        Usuario p = null;

        if (principal != null) {
            p = usuarioRepositorio.findByEmail(principal.getName());
            
            model.addAttribute("principal", p);

            if (p == null) {
                try {
                    request.logout();
                } catch (Exception e) {
                }
            }
        }

        if (u == null) {
            throw new BadRequestException("Usuário precisa estar logado.");
        }

        CandidaturaServico c = new CandidaturaServico(s, u, "pendente", LocalDate.now());
        candidaturaServicoService.inserir(c);

        return "redirect:/querovoluntariar";
    }
}
