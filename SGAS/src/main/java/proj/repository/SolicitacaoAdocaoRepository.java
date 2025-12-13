package proj.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.model.SolicitacaoAdocao;
import proj.model.SolicitacaoAdocaoKey;

public interface SolicitacaoAdocaoRepository extends JpaRepository<SolicitacaoAdocao, SolicitacaoAdocaoKey> {
	
	 Optional<SolicitacaoAdocao> findById_UsuarioIdAndId_AnimalId(Long usuarioId, Long animalId);
	 
	 ArrayList<SolicitacaoAdocao> findByUsuarioId(long usuarioId);
	 
	 boolean existsByUsuarioIdAndAnimalId(Long usuarioId, Long animalId);

}
