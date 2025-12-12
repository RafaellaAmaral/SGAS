package proj.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proj.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
	Animal findByNome(String nome); //Tem que ter indice unico na tabela
	
	ArrayList<Animal> findBySexo(String sexo);
	
	ArrayList<Animal> findByPorte(String porte);
	
	ArrayList<Animal> findByRaca(String raca);

}
