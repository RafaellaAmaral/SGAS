package proj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class SolicitacaoAdocao {

	@Id
	private long id;
	@OneToOne
    @JoinColumn(name = "animal_id")
	private Animal animal;
	@OneToOne
    @JoinColumn(name = "tutor_usuario_id")
	private Tutor tutor;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Animal getAnimal() {
		return animal;
	}
	
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
	public Tutor getTutor() {
		return tutor;
	}
	
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
}
