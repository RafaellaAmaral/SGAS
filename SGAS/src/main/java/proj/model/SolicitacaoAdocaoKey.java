package proj.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class SolicitacaoAdocaoKey implements Serializable {
	
private static final long serialVersionUID = 1L;
	

	@Column(name = "usuario_id")
    Long usuarioId;

    @Column(name = "animal_id")
    Long animalId;
    
    public SolicitacaoAdocaoKey(Long u, Long a) {
    	this.usuarioId = u;
    	this.animalId = a;
    }


}
