package proj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proj.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal findByNome(String nome);

    List<Animal> findBySexo(String sexo);

    List<Animal> findByPorte(String porte);

    List<Animal> findByRaca(String raca);
}
