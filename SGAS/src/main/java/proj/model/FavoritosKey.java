package proj.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FavoritosKey implements Serializable {
	
private static final long serialVersionUID = 1L;
	

	@Column(name = "usuario_id")
    Long usuarioId;

    @Column(name = "animal_id")
    Long animalId;
    
    public FavoritosKey(Long u, Long a) {
    	this.usuarioId = u;
    	this.animalId = a;
    }

}
