package proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.model.Animal;
import proj.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	public List<Animal> listarTodos() {
		
		List<Animal> listaAnimais = animalRepository.findAll();
		return listaAnimais;
		
	}
	
	public void inserir(Animal a) {
		animalRepository.save(a);
	}
	
	public void alterar(Animal a) {
		animalRepository.save(a);
	}
	
	public void excluir(long id) {
		Animal a = animalRepository.findById(id).get();
		
		animalRepository.delete(a);
	}
	
	public Animal buscarPeloId(long id) {
		return animalRepository.getById(id);
	}

}
