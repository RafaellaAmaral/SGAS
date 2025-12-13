package proj.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
//@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CandidaturaServicoKey implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Column(name = "usuario_id")
    Long usuarioId;

    @Column(name = "servico_id")
    Long servicoId;
    
    public CandidaturaServicoKey(Long u, Long s) {
    	this.usuarioId = u;
    	this.servicoId = s;
    }

    // standard constructors, getters, and setters
    // hashcode and equals implementation
}