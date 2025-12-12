package proj.model;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SolicitacaoAdocao {

	@EqualsAndHashCode.Include
    @EmbeddedId
    SolicitacaoAdocaoKey id;
	
	@ManyToOne
	@MapsId("animalId")
    @JoinColumn(name = "animal_id")
	private Animal animal;
	
	@ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    Usuario usuario;
	
	private String status;
	
	private LocalDate dataSolicitacao;
	
    // additional properties
    // standard constructors, getters, and setters	
}
