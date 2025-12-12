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

}
