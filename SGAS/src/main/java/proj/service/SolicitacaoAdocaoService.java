package proj.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.model.CandidaturaServico;
import proj.model.SolicitacaoAdocao;
import proj.model.SolicitacaoAdocaoKey;
import proj.repository.SolicitacaoAdocaoRepository;

@Service
public class SolicitacaoAdocaoService {
	
	@Autowired
	SolicitacaoAdocaoRepository solicitacaoAdocaoRepository;
	
	public List<SolicitacaoAdocao> listarTodos() {
		
		List<SolicitacaoAdocao> listaCandidaturas = solicitacaoAdocaoRepository.findAll();
		return listaCandidaturas;
	}
	
	public List<SolicitacaoAdocao> listarTodosPendendes() {

	    List<SolicitacaoAdocao> listaSolicitacoes = solicitacaoAdocaoRepository.findAll();

	    listaSolicitacoes.sort(Comparator.comparing(
	        c -> !c.getStatus().equalsIgnoreCase("pendente")
	    ));

	    return listaSolicitacoes;
	}
	
	public List<SolicitacaoAdocao> listarSolicitacoesPorUsuario(long usuarioId) {

	    List<SolicitacaoAdocao> listaSolicitacoes = solicitacaoAdocaoRepository.findByUsuarioId(usuarioId);

	    return listaSolicitacoes;
	}
	
	public void inserir(SolicitacaoAdocao c) {
		solicitacaoAdocaoRepository.save(c);
	}
	
	public void alterar(SolicitacaoAdocao c) {
		solicitacaoAdocaoRepository.save(c);
	}
	
	public void excluir(SolicitacaoAdocaoKey id) {
		SolicitacaoAdocao c = solicitacaoAdocaoRepository.findById(id).get();
		
		solicitacaoAdocaoRepository.delete(c);
	}
	
	public SolicitacaoAdocao buscarPeloId(SolicitacaoAdocaoKey id) {
		return solicitacaoAdocaoRepository.getById(id);
	}
	
	public void aprovar(long usuarioId, long animalId) {
	    SolicitacaoAdocao s = solicitacaoAdocaoRepository.findById_UsuarioIdAndId_AnimalId(usuarioId, animalId).orElseThrow();
	    s.setStatus("aprovada");
	    solicitacaoAdocaoRepository.save(s);
	}

	public void recusar(long usuarioId, long animalId) {
		SolicitacaoAdocao s = solicitacaoAdocaoRepository.findById_UsuarioIdAndId_AnimalId(usuarioId, animalId).orElseThrow();
	    s.setStatus("recusada");
	    solicitacaoAdocaoRepository.save(s);
	}
	
	public boolean isFavorito(Long usuarioId, Long animalId) {
        return solicitacaoAdocaoRepository.existsByUsuarioIdAndAnimalId(usuarioId, animalId);
    }
}
