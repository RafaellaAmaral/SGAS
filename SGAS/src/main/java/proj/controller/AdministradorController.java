package proj.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import proj.exception.BadRequestException;
import proj.model.Animal;
import proj.model.CandidaturaServico;
import proj.model.Servico;
import proj.model.SolicitacaoAdocao;
import proj.model.Usuario;
import proj.repository.UsuarioRepository;
import proj.service.AnimalService;
import proj.service.CandidaturaServicoService;
import proj.service.ServicoService;
import proj.service.SolicitacaoAdocaoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    CandidaturaServicoService candidaturaServicoService;
    
    @Autowired
    SolicitacaoAdocaoService solicitacaoAdocaoService;
    
    @Autowired
    UsuarioRepository usuarioRepositorio;
    
    @Autowired
    AnimalService animalService;
    
    @Autowired
    ServicoService servicoService;
    
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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }

        try {
            List<CandidaturaServico> listaCandidaturas = candidaturaServicoService.listarTodosPendendes();
            model.addAttribute("listaCandidaturas", listaCandidaturas);
        } catch (Exception e) {
        }
        
        return "admin/pag-inicial";
    }

    @GetMapping("/listasolicitacoes")
    public String mostraSolicitacoesAdocao(Model model, Principal principal, HttpServletRequest request)
            throws SQLException {

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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }

        try {
            List<SolicitacaoAdocao> listaSolicitacoes = solicitacaoAdocaoService.listarTodosPendendes();
            model.addAttribute("listaSolicitacoes", listaSolicitacoes);
        } catch (Exception e) {
        }

        return "admin/solicitacoes-adocao";
    }

    @PostMapping("/listasolicitacoes/aprovar/{usuario_id}/{animal_id}")
    public String aprovarAdocao(@PathVariable Long usuario_id, @PathVariable Long animal_id) {
        solicitacaoAdocaoService.aprovar(usuario_id, animal_id);
        return "redirect:/administrador/listasolicitacoes";
    }
    
    @PostMapping("/listasolicitacoes/recusar/{usuario_id}/{animal_id}")
    public String recusarAdocao(@PathVariable Long usuario_id, @PathVariable Long animal_id) {
        candidaturaServicoService.recusar(usuario_id, animal_id);
        return "redirect:/administrador/listasolicitacoes";
    }
    
    
    @GetMapping("/listacandidaturas")
    public String mostraSolicitacoesServico(Model model, Principal principal, HttpServletRequest request)
            throws SQLException {

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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }

        try {
            List<CandidaturaServico> listaCandidaturas = candidaturaServicoService.listarTodosPendendes();
            model.addAttribute("listaCandidaturas", listaCandidaturas);
        } catch (Exception e) {
        }

        return "admin/solicitacoes-trabalho";
    }

    @PostMapping("/listacandidaturas/aprovar/{usuario_id}/{servico_id}")
    public String aprovarServico(@PathVariable Long usuario_id, @PathVariable Long servico_id) {
        candidaturaServicoService.aprovar(usuario_id, servico_id);
        return "redirect:/administrador/listacandidaturas";
    }
    
    @PostMapping("/listacandidaturas/recusar/{usuario_id}/{servico_id}")
    public String recusarServico(@PathVariable Long usuario_id, @PathVariable Long servico_id) {
        candidaturaServicoService.recusar(usuario_id, servico_id);
        return "redirect:/administrador/listacandidaturas";
    }
    

    @GetMapping("/pag-inicial")
    public String pagInicial() {
        return "admin/pag-inicial";
    }

    @GetMapping("/listar-animais")
    public String listarAnimais(Model model, Principal principal, HttpServletRequest request) {
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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }

        try {
            List<CandidaturaServico> listaCandidaturas = candidaturaServicoService.listarTodosPendendes();
            model.addAttribute("listaCandidaturas", listaCandidaturas);
        } catch (Exception e) {
        }
        
        model.addAttribute("animais", animalService.listarTodos());
        return "admin/listar-animais";
    }

    @GetMapping("/cadastrar-animal")
    public String cadastrarAnimalForm(Model model, Principal principal, HttpServletRequest request) {
        
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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }
        
        return "admin/cadastrar-animal";
    }

    @PostMapping("/cadastrar-animal2")
    public String cadastrarAnimal(
            @RequestParam("nome") String nome,
            @RequestParam("raca") String raca,
            @RequestParam("idade") int idade,
            @RequestParam("porte") String porte,
            @RequestParam("descricao") String descricao,
            @RequestParam("sexo") String sexo,
            @RequestParam("imagem") MultipartFile imagem,
            RedirectAttributes redirectAttributes, Model model, Principal principal, HttpServletRequest request) {
        
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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }

        Animal animal = new Animal();
        animal.setNome(nome);
        animal.setRaca(raca);
        animal.setDescricao(descricao);
        animal.setPorte(porte);
        animal.setSexo(sexo);
        animal.setDataNascimento(LocalDate.now().minusYears(idade));

        String imageUrl = "";

        if (imagem != null && !imagem.isEmpty()) {
            try {
                String uploadDir = "src/main/resources/static/images/animais/";
                Files.createDirectories(Paths.get(uploadDir));
                Path caminho = Paths.get(uploadDir + imagem.getOriginalFilename());
                Files.copy(imagem.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);
                imageUrl = "/images/animais/" + imagem.getOriginalFilename();
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("erro", "Erro ao fazer upload da imagem.");
                return "redirect:/administrador/cadastrar-animal";
            }
        }

        animal.setImagemUrl(imageUrl);
        animalService.inserir(animal);
        redirectAttributes.addFlashAttribute("mensagem", "Animal cadastrado com sucesso!");
        return "redirect:/administrador/listar-animais";
    }

    @GetMapping("/editar-animal/{id}")
    public String editarAnimalViaAdmin(@PathVariable("id") long id, Model model, Principal principal, HttpServletRequest request) {
        
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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }
        
        model.addAttribute("animal", animalService.buscarPeloId(id));
        return "admin/editar-animal";
    }

    @GetMapping("/remover-animal/{id}")
    public String removerAnimalViaAdmin(@PathVariable("id") long id) {
        animalService.excluir(id);
        return "redirect:/administrador/listar-animais";
    }

    @PostMapping("/editar-animal")
    public String atualizarAnimalViaAdmin(
            @RequestParam("id") long id,
            @RequestParam("nome") String nome,
            @RequestParam("raca") String raca,
            @RequestParam("idade") int idade,
            @RequestParam("porte") String porte,
            @RequestParam("descricao") String descricao,
            @RequestParam("sexo") String sexo) {

        Animal animal = animalService.buscarPeloId(id);

        if (animal != null) {
            animal.setNome(nome);
            animal.setRaca(raca);
            animal.setDescricao(descricao);
            animal.setPorte(porte);
            animal.setSexo(sexo);
            animal.setDataNascimento(LocalDate.now().minusYears(idade));
            animalService.alterar(animal);
        }

        return "redirect:/administrador/listar-animais";
    }

    @GetMapping("/listar-servicos")
    public String listarServicos(Model model, Principal principal, HttpServletRequest request) {
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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }

        try {
            List<CandidaturaServico> listaCandidaturas = candidaturaServicoService.listarTodosPendendes();
            model.addAttribute("listaCandidaturas", listaCandidaturas);
        } catch (Exception e) {
        }
        
        model.addAttribute("servicos", servicoService.listarTodos());
        return "admin/listar-servicos";
    }

    @GetMapping("/cadastrar-servico")
    public String cadastrarServicoForm(Model model, Principal principal, HttpServletRequest request) {
        
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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }
        
        return "admin/cadastrar-servico";
    }

    @PostMapping("/cadastrar-servico2")
    public String cadastrarServico(
            @RequestParam("titulo") String titulo,
            @RequestParam("descricao") String descricao,
            @RequestParam("tipo") String tipo,
            @RequestParam("turno") String turno,
            @RequestParam("vagas") int vagas,
            @RequestParam("data") String dataStr,
            RedirectAttributes redirectAttributes, Model model, Principal principal, HttpServletRequest request) {
        
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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }

        Servico servico = new Servico();
        servico.setTitulo(titulo);
        servico.setDescricao(descricao);
        servico.setTipo(tipo);
        servico.setTurno(turno);
        servico.setVagas(vagas);
        servico.setData(LocalDate.parse(dataStr));

        servicoService.inserir(servico);
        redirectAttributes.addFlashAttribute("mensagem", "Serviço cadastrado com sucesso!");
        return "redirect:/administrador/listar-servicos";
    }

    @GetMapping("/editar-servico/{id}")
    public String editarServicoViaAdmin(@PathVariable("id") long id, Model model, Principal principal, HttpServletRequest request) {
        
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

        if (u == null || u.isAdministrador() == false) {
            throw new BadRequestException("Usuário Administrador precisa estar logado.");
        }
        
        model.addAttribute("servico", servicoService.buscarPeloId(id));
        return "admin/editar-servico";
    }

    @GetMapping("/remover-servico/{id}")
    public String removerServicoViaAdmin(@PathVariable("id") long id) {
        servicoService.excluir(id);
        return "redirect:/administrador/listar-servicos";
    }

    @PostMapping("/editar-servico")
    public String atualizarServicoViaAdmin(
            @RequestParam("id") long id,
            @RequestParam("titulo") String titulo,
            @RequestParam("descricao") String descricao,
            @RequestParam("tipo") String tipo,
            @RequestParam("turno") String turno,
            @RequestParam("vagas") int vagas,
            @RequestParam("data") String dataStr) {

        Servico servico = servicoService.buscarPeloId(id);

        if (servico != null) {
            servico.setTitulo(titulo);
            servico.setDescricao(descricao);
            servico.setTipo(tipo);
            servico.setTurno(turno);
            servico.setVagas(vagas);
            servico.setData(LocalDate.parse(dataStr));
            servicoService.alterar(servico);
        }

        return "redirect:/administrador/listar-servicos";
    }
    
}