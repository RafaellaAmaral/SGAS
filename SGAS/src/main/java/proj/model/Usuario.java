package proj.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuario")
public class Usuario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    
	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "nome", nullable = false)
	private String nome;

	@com.fasterxml.jackson.annotation.JsonIgnore
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "is_administrador", nullable = false)
	private boolean isAdministrador = false;
	
//	@ManyToMany
//	@JoinTable(name = "user_tem_role", 
//			  joinColumns = @JoinColumn(name = "usuario_id"), 
//			  inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private List<Role> roles = new ArrayList<>();	
	
	@ManyToMany
	@JoinTable(name = "usario_fav_animal", 
	  joinColumns = @JoinColumn(name = "usuario_id"), 
	  inverseJoinColumns = @JoinColumn(name = "animal_id"))
	private List<Animal> favoritos  = new ArrayList<>();	
	
	
	@OneToMany(mappedBy = "usuario")
    Set<CandidaturaServico> candidaturas = new HashSet<>();
	
	@OneToMany(mappedBy = "usuario")
    Set<SolicitacaoAdocao> solicitacoesAdocao = new HashSet<>();
	
}
