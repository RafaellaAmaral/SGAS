package proj.model;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usario_fav_animal")
public class Favoritos {
	
	
	
	@EqualsAndHashCode.Include
    @EmbeddedId
    FavoritosKey id;
	
	@ManyToOne
	@MapsId("animalId")
    @JoinColumn(name = "animal_id")
	private Animal animal;
	
	@ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    Usuario usuario;
	
	public Favoritos(Animal animal, Usuario usuario) {
        this.animal = animal;
        this.usuario = usuario;
        this.id = new FavoritosKey(usuario.getId(), animal.getId());
    }
	
}
