package proj.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import proj.model.Animal;
import proj.model.SolicitacaoAdocao;
import proj.model.SolicitacaoAdocaoKey;
import proj.model.Usuario;
import proj.repository.UsuarioRepository;
import proj.service.AnimalService;
import proj.service.SolicitacaoAdocaoService;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    UsuarioRepository usuarioRepositorio;
    
    @Autowired
    AnimalService animalService;
    
    @Autowired
    SolicitacaoAdocaoService solicitacaoAdocaoService;
    
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

    @GetMapping("/quero-adotar")
    public String queroAdotar(Model model, Principal principal) {
        Usuario u = null;
        if (principal != null) {
            u = usuarioRepositorio.findByEmail(principal.getName());
        }
        model.addAttribute("principal", u);
        
        // Buscar todos os animais (apenas cães)
        model.addAttribute("animais", animalService.listarTodos());
        
        return "quero-adotar";
    }
    
    @PostMapping("/solicitar-adocao")
    public String solicitarAdocao(@RequestParam("animalId") Long animalId, 
                                  @RequestParam("nome") String nome, 
                                  Principal principal, 
                                  RedirectAttributes redirectAttributes) {
        if (principal == null) {
            return "redirect:/login";  // Redireciona se não logado
        }
        
        Usuario usuario = usuarioRepositorio.findByEmail(principal.getName());
        Animal animal = animalService.buscarPeloId(animalId);
        
        if (usuario != null && animal != null) {
            // Criar a chave composta
            SolicitacaoAdocaoKey key = new SolicitacaoAdocaoKey();
            key.setAnimalId(animalId);
            key.setUsuarioId(usuario.getId());
            
            SolicitacaoAdocao solicitacao = new SolicitacaoAdocao();
            solicitacao.setId(key);
            solicitacao.setAnimal(animal);
            solicitacao.setUsuario(usuario);
            solicitacao.setStatus("pendente");  // Status inicial
            solicitacao.setDataSolicitacao(LocalDate.now());  // Data atual
            
            solicitacaoAdocaoService.inserir(solicitacao);
            redirectAttributes.addFlashAttribute("mensagem", "Solicitação de adoção enviada com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Erro ao enviar solicitação.");
        }
        
        return "redirect:/quero-adotar";
    }
    
    @GetMapping("/voluntarios")
    public String voluntarios(Model model, Principal principal) {
        Usuario u = null;
        if (principal != null) {
            u = usuarioRepositorio.findByEmail(principal.getName());
        }
        model.addAttribute("principal", u);
        return "voluntarios";
    }

    @GetMapping("/doacao")
    public String doacao(Model model, Principal principal) {
        Usuario u = null;
        if (principal != null) {
            u = usuarioRepositorio.findByEmail(principal.getName());
        }
        model.addAttribute("principal", u);
        return "doacao";
    }
}