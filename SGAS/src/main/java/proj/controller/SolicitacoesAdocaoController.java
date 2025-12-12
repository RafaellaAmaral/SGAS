package proj.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proj.model.SolicitacaoAdocao;
import proj.service.SolicitacaoAdocaoService;

@Controller
@RequestMapping("/administrador")
public class SolicitacoesAdocaoController {

	@Autowired
	SolicitacaoAdocaoService SolicitacaoAdocaoService;

/*	@GetMapping("/listasolicitacoes")
	public String mostraSolicitacoesAdocao(Model model, Principal principal) throws SQLException {

		System.out.println("✅ ENTROU NO CONTROLLER /listasolicitacoes");

		List<SolicitacaoAdocao> listaSolicitacoes = SolicitacaoAdocaoService.listarTodos();

		model.addAttribute("listaSolicitacoes", listaSolicitacoes);
		
		return "solicitacoes-trabalho";
	} */

	/*
	 * @PostMapping("/aprovar/{id}") public String aprovar(@PathVariable long id) {
	 * SolicitacaoAdocaoService.aprovar(id); return
	 * "redirect:/administrador/listacandidaturas"; }
	 * 
	 * @PostMapping("/negar/{id}") public String negar(@PathVariable long id) {
	 * SolicitacaoAdocaoService.negar(id); return
	 * "redirect:/administrador/listacandidaturas"; }
	 */
}
