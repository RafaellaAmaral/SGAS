package proj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proj.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {		
	
	Usuario findByNome(String nome);

}
