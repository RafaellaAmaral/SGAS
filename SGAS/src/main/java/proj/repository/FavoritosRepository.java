package proj.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.model.Favoritos;
import proj.model.FavoritosKey;

public interface FavoritosRepository extends JpaRepository<Favoritos, FavoritosKey>{
	
	ArrayList<Favoritos> findByUsuarioId(long usuarioId);
	
	boolean existsByUsuarioIdAndAnimalId(Long usuarioId, Long animalId);

}
