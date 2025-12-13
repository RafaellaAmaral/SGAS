package proj.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.model.CandidaturaServico;
import proj.model.CandidaturaServicoKey;
import proj.model.Servico;

public interface CandidaturaServicoRepository extends JpaRepository<CandidaturaServico, CandidaturaServicoKey> {
	
    // Método personalizado que busca diretamente pelos campos aninhados na chave 'id'
    Optional<CandidaturaServico> findById_UsuarioIdAndId_ServicoId(Long usuarioId, Long servicoId);
    

	ArrayList<CandidaturaServico> findByStatus(String status);
	
	ArrayList<CandidaturaServico> findByServicoId(long servicoId);
	
	ArrayList<CandidaturaServico> findByUsuarioId(long usuarioId);
 
}
