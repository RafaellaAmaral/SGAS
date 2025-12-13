package proj.model;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


//@Getter
//@Setter
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class CandidaturaServico {

	@EqualsAndHashCode.Include
    @EmbeddedId
    CandidaturaServicoKey id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    Usuario usuario;

    @ManyToOne
    @MapsId("servicoId")
    @JoinColumn(name = "servico_id")
    Servico servico;

	private LocalDate dataCandidatura;
	private String status;
	
	public CandidaturaServico(Servico servico, Usuario usuario, String stat, LocalDate data) {
		this.servico = servico;
        this.usuario = usuario;
        this.status = stat;
        this.dataCandidatura = data;
        this.id = new CandidaturaServicoKey(usuario.getId(), servico.getId());
	}
    
    // standard constructors, getters, and setters
}