package proj.service;

import java.util.List;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.model.CandidaturaServico;
import proj.repository.CandidaturaServicoRepository;

@Service
public class CandidaturaServicoService {
	
	@Autowired
	CandidaturaServicoRepository candidaturaServicoRepository;
	
	public List<CandidaturaServico> listarTodos() {
		
		List<CandidaturaServico> listaCandidaturas = candidaturaServicoRepository.findAll();
		return listaCandidaturas;
	}
	
	public List<CandidaturaServico> listarTodosPendendes() {

	    List<CandidaturaServico> listaCandidaturas = candidaturaServicoRepository.findAll();

	    listaCandidaturas.sort(Comparator.comparing(
	        c -> !c.getStatus().equalsIgnoreCase("pendente")
	    ));

	    return listaCandidaturas;
	}
	
	public List<CandidaturaServico> listarCandidaturasPorUsuario(long usuarioId) {

	    List<CandidaturaServico> listaCandidaturas = candidaturaServicoRepository.findByUsuarioId(usuarioId);

	    return listaCandidaturas;
	}
	
	public void inserir(CandidaturaServico c) {
		candidaturaServicoRepository.save(c);
	}
	
	public void alterar(CandidaturaServico c) {
		candidaturaServicoRepository.save(c);
	}
	
	public void excluir(long usuarioId, long servicoId) {
		CandidaturaServico c = candidaturaServicoRepository.findById_UsuarioIdAndId_ServicoId(usuarioId, servicoId).get();
		
		candidaturaServicoRepository.delete(c);
	}
	
	public CandidaturaServico buscarPeloId(long usuarioId, long servicoId) {
		return candidaturaServicoRepository.findById_UsuarioIdAndId_ServicoId(usuarioId, servicoId).get();
	}
	
	public void aprovar(long usuarioId, long servicoId) {
	    CandidaturaServico c = candidaturaServicoRepository.findById_UsuarioIdAndId_ServicoId(usuarioId, servicoId).orElseThrow();
	    c.setStatus("aprovada");
	    candidaturaServicoRepository.save(c);
	}

	public void recusar(long usuarioId, long servicoId) {
	    CandidaturaServico c = candidaturaServicoRepository.findById_UsuarioIdAndId_ServicoId(usuarioId, servicoId).orElseThrow();
	    c.setStatus("recusada");
	    candidaturaServicoRepository.save(c);
	}
	
	public List<CandidaturaServico> listarPendentes() {
	    return candidaturaServicoRepository.findByStatus("PENDENTE");
	}
	
	public boolean isFavorito(Long usuarioId, Long servicoId) {
        return candidaturaServicoRepository.existsByUsuarioIdAndServicoId(usuarioId, servicoId);
    }

}
