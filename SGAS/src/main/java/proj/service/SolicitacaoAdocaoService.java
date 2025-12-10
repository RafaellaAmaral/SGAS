package proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.model.SolicitacaoAdocao;
import proj.repository.SolicitacaoAdocaoRepository;

@Service
public class SolicitacaoAdocaoService {
	
	@Autowired
	SolicitacaoAdocaoRepository solicitacaoAdocaoRepository;
	
	public List<SolicitacaoAdocao> listarTodos() {
		
		List<SolicitacaoAdocao> listaCandidaturas = solicitacaoAdocaoRepository.findAll();
		return listaCandidaturas;
	}
	
	public void inserir(SolicitacaoAdocao c) {
		solicitacaoAdocaoRepository.save(c);
	}
	
	public void alterar(SolicitacaoAdocao c) {
		solicitacaoAdocaoRepository.save(c);
	}
	
	public void excluir(long id) {
		SolicitacaoAdocao c = solicitacaoAdocaoRepository.findById(id).get();
		
		solicitacaoAdocaoRepository.delete(c);
	}
	
	public SolicitacaoAdocao buscarPeloId(long id) {
		return solicitacaoAdocaoRepository.getById(id);
	}
}
