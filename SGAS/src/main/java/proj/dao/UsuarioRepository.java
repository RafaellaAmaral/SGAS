package proj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {		
	
	Usuario findByNome(String nome);

}
