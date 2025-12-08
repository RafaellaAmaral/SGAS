package proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.dao.AnimalRepository;
import proj.model.Animal;

@Service
public class AnimalService {
	
    @Autowired
    private AnimalRepository animalRepository;
	
    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }
	
    public void inserir(Animal a) {
        animalRepository.save(a);
    }
	
    public void alterar(Animal a) {
        animalRepository.save(a);
    }
	
    public void excluir(long id) {
        animalRepository.deleteById(id);
    }
	
    public Animal buscarPeloId(long id) {
        return animalRepository.findById(id).orElse(null);
    }
}
