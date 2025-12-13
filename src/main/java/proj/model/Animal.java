package proj.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Animal {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private String nome;
	private String raca;
	private LocalDate dataNascimento;
	@Column(length = 1)
	private String porte; // P, M, G
	private String descricao;
	private String imagemUrl;
    @Column(length = 1)
    private String sexo; //M, F	
	
	
	@ManyToMany
	@JoinTable(name = "usario_fav_animal", 
	  joinColumns = @JoinColumn(name = "animal_id"), 
	  inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> favoritoDe  = new ArrayList<>();	
}
