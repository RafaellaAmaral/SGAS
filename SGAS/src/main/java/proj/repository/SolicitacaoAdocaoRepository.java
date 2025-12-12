package proj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.model.SolicitacaoAdocao;

public interface SolicitacaoAdocaoRepository extends JpaRepository<SolicitacaoAdocao, Long> {
	
	 Optional<SolicitacaoAdocao> findById_UsuarioIdAndId_AnimalId(Long usuarioId, Long animalId);

}
