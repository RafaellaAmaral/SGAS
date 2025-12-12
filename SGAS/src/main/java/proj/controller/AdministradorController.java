package proj.controller;

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

import jakarta.servlet.http.HttpServletRequest;
import proj.exception.BadRequestException;
import proj.model.Animal;
import proj.model.CandidaturaServico;
import proj.model.Usuario;
import proj.repository.UsuarioRepository;
import proj.service.AnimalService;
import proj.service.CandidaturaServicoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {


	@Autowired
	CandidaturaServicoService candidaturaServicoService;
	
	@Autowired
	UsuarioRepository usuarioRepositorio;
	
    @Autowired
    AnimalService animalService;

	
	@GetMapping
	public String mostraPaginaInicialAdm(Model model, Principal principal) {
		
		Usuario u = null;
		
		if (principal != null)
    	{
    		u = usuarioRepositorio.findByEmail(principal.getName());
    	}
		
		model.addAttribute("principal", u);

		return "admin/pag-inicial";
	}
	
	@GetMapping("/listacandidaturas")
	public String mostraSolicitacoesServico(Model model, Principal principal, HttpServletRequest request) 
	throws SQLException
	{
		
		System.out.println("✅ ENTROU NO CONTROLLER /listacandidaturas");
		
    	Usuario u = null;
    	
    	//verificar se o cookie de sessao recebido ainda tem um usuario cadastrado no banco.
    	if (principal != null)
    	{
    		u = usuarioRepositorio.findByEmail(principal.getName());
    		
    		model.addAttribute("principal", u);
    		
    		if (u == null) { // existe sessão mas sem usuario cadastrado
    			try{request.logout();}catch(Exception e) {} //remover a sessao.
    			System.out.println("SESSAO removida.");
    		}
    	}
    	
    	if (u == null || u.isAdministrador() == false)
    		throw new BadRequestException("Usuário Administrador precisa estar logado.");

    	
		
		try {
			List<CandidaturaServico> listaCandidaturas = candidaturaServicoService.listarTodosPendendes();
			
			model.addAttribute("listaCandidaturas", listaCandidaturas);
			

/*			Usuario u = UsuarioDao.getByNome(conn, principal.getName());
			String nome = null;
			
			if(u.getRole().equals("Empresa")) {
				Empresa x = EmpresaDao.getByUsuario_id(conn, u.getId());
				nome = x.getNome();
			}
			else  {
				Professor x = ProfessorDao.getByUsuario_id(conn, u.getId());
				nome = x.getNome();
			}
			System.out.println("NOME: "+nome);
			
			model.addAttribute("nome", nome);
			
			model.addAttribute("listaCursos", listaCursos);
			model.addAttribute("listaCampus", listaCampus);
			model.addAttribute("listaPeriodos", listaPeriodos);
			model.addAttribute("listaAlunos", listaAlunos);
		}

		model.addAttribute("pesqNome", new PesquisaNomeAluno());
		model.addAttribute("pesqFiltro", new PesquisaFiltrosAluno());
		return "pesquisa/pesquisaDeAlunos";
	*/
	}
		catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
		}

		return "admin/solicitacoes-trabalho";
	}
	
	
	@PostMapping("/aprovar/{usuario_id}/{servico_id}")
	public String aprovar(@PathVariable Long usuario_id, @PathVariable Long servico_id) {
	    candidaturaServicoService.aprovar(usuario_id, servico_id);
	    return "redirect:/administrador/listacandidaturas";
	}

/*	@PostMapping("/negar/{id}")
	public String negar(@PathVariable long id) {
	    candidaturaServicoService.negar(id);
	    return "redirect:/administrador/listacandidaturas";
	} */




    @GetMapping("/pag-inicial")
    public String pagInicial() {
        return "admin/pag-inicial";
    }

    @GetMapping("/listar-animais")
    public String listarAnimais() {
        return "admin/listar-animais";
    }

    @GetMapping("/cadastrar-animal")
    public String cadastrarAnimalForm() {
        return "admin/cadastrar-animal";
    }

    @PostMapping("/cadastrar-animal")
    public String cadastrarAnimal(
            @RequestParam("nome") String nome,
            @RequestParam("raca") String raca,
            @RequestParam("idade") int idade,
            @RequestParam("porte") String porte,
            @RequestParam("descricao") String descricao,
            @RequestParam("sexo") String sexo) {

        Animal animal = new Animal();

        animal.setNome(nome);
        animal.setRaca(raca);
        animal.setDescricao(descricao);
        animal.setPorte(porte);
        animal.setSexo(sexo);
        animal.setDataNascimento(LocalDate.now().minusYears(idade));

        animalService.inserir(animal);

        return "redirect:/administrador/listar-animais";
    }

    @GetMapping("/editar-animal/{id}")
    public String editarAnimalViaAdmin(@PathVariable("id") long id, Model model) {
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

}
