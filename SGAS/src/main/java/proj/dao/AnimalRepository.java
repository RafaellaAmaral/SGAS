package proj.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
	
	Animal findByNome(String nome);
	
	ArrayList<Animal> findBySexo(byte sexo);
	
	ArrayList<Animal> findByPorte(byte porte);
	
	ArrayList<Animal> findByRaca(byte raca);

}
