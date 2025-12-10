package proj.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class SolicitacaoAdocao {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "animal_id")
	private Animal animal;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	private String status;
	
	private LocalDate dataSolicitacao;
	
    // additional properties
    // standard constructors, getters, and setters	
}
