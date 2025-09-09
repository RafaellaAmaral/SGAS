package proj.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proj.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
	
	Servico findByTitulo(String titulo);
	
	ArrayList<Servico> findByTurno(String turno);
	
	ArrayList<Servico> findByTipo(String tipo);

}