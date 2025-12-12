package proj.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import proj.exception.BadRequestException;
import proj.model.CandidaturaServico;
import proj.model.Favoritos;
import proj.model.SolicitacaoAdocao;
import proj.model.Usuario;
import proj.repository.UsuarioRepository;
import proj.service.CandidaturaServicoService;
import proj.service.FavoritosService;
import proj.service.SolicitacaoAdocaoService;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import proj.dto.LoginDTO;
import proj.dto.UsuarioDTO;
import proj.model.Usuario;
import proj.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepositorio;
	
	@Autowired
	CandidaturaServicoService candidaturaServicoService;
	
	@Autowired
	SolicitacaoAdocaoService solicitacaoAdocaoService;
	
	@Autowired
	FavoritosService favoritosService;
	
	private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    public UsuarioController() {
    	this.usuarioService = null;
    }
	
	@GetMapping
    public String mostraIndexAdmin(Model model, Principal principal, HttpServletRequest request) {
    	
    	Usuario u = null;

        if (principal != null) {
            u = usuarioRepositorio.findByEmail(principal.getName());
            
            model.addAttribute("principal", u);

            if (u == null) {
                try {
                    request.logout();
                } catch (Exception e) {
                }
            }
        }
    	
    	return "usuario/pag-inicial";
    }
	
	@GetMapping("/meusservicos")
	public String mostraMeusServicos(Model model, Principal principal, HttpServletRequest request) {
		
		Usuario u = null;

        if (principal != null) {
            u = usuarioRepositorio.findByEmail(principal.getName());
            
            model.addAttribute("principal", u);

            if (u == null) {
                try {
                    request.logout();
                } catch (Exception e) {
                }
            }
        }
        
        List<CandidaturaServico> listaCandidaturas = candidaturaServicoService.listarCandidaturasPorUsuario(u.getId());
        
        model.addAttribute("listaCandidaturasUsuario", listaCandidaturas);
    	
    	return "usuario/lista-candidaturas";
		
	}
	
	@GetMapping("/minhasadocoes")
	public String mostraMinhasAdocoes(Model model, Principal principal, HttpServletRequest request) {
		
		Usuario u = null;

        if (principal != null) {
            u = usuarioRepositorio.findByEmail(principal.getName());
            
            model.addAttribute("principal", u);

            if (u == null) {
                try {
                    request.logout();
                } catch (Exception e) {
                }
            }
        }
        
        List<SolicitacaoAdocao> listaSolicitacoes = solicitacaoAdocaoService.listarSolicitacoesPorUsuario(u.getId());
        
        model.addAttribute("listaSolicitacoesUsuario", listaSolicitacoes);
    	
    	return "usuario/lista-solicitacoes";
		
	}
	
	@GetMapping("/favoritos")
	public String mostraFavoritos(Model model, Principal principal, HttpServletRequest request) {
		
		Usuario u = null;

        if (principal != null) {
            u = usuarioRepositorio.findByEmail(principal.getName());
            
            model.addAttribute("principal", u);

            if (u == null) {
                try {
                    request.logout();
                } catch (Exception e) {
                }
            }
        }
        
        List<Favoritos> listaFavoritos = favoritosService.listarFavoritosPorUsuario(u.getId());
        
        model.addAttribute("listaFavoritos", listaFavoritos);
    	
    	return "usuario/favoritos";
		
	}
	
	
	@GetMapping("/cadastro")
    public String cadastroForm() {
        return "cadastro_usuario";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@Valid @RequestBody UsuarioDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Usuario u = usuarioService.register(dto);
            Map<String, Object> out = new HashMap<>();
            out.put("id", u.getId());
            out.put("nome", u.getNome());
            out.put("email", u.getEmail());
            out.put("isAdministrador", u.isAdministrador());
            return ResponseEntity.status(HttpStatus.CREATED).body(out);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Falha ao cadastrar usuário"));
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Usuario u = usuarioService.login(dto);
            Map<String, Object> out = new HashMap<>();
            out.put("id", u.getId());
            out.put("nome", u.getNome());
            out.put("email", u.getEmail());
            out.put("isAdministrador", u.isAdministrador());
            return ResponseEntity.ok(out);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Falha ao efetuar login"));
        }
    }

}
