package proj.service;

import java.util.List;

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
	    c.setStatus("APROVADA");
	    candidaturaServicoRepository.save(c);
	}

	public void negar(long usuarioId, long servicoId) {
	    CandidaturaServico c = candidaturaServicoRepository.findById_UsuarioIdAndId_ServicoId(usuarioId, servicoId).orElseThrow();
	    c.setStatus("NEGADA");
	    candidaturaServicoRepository.save(c);
	}
	
	public List<CandidaturaServico> listarPendentes() {
	    return candidaturaServicoRepository.findByStatus("PENDENTE");
	}

}
