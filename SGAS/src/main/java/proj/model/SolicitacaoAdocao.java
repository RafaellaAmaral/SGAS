package proj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SolicitacaoAdocao {

	@Id
	private long id;
	private Animal animal;
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
