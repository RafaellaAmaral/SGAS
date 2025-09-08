package proj.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
	
	Servico findByTitulo(String titulo);
	
	ArrayList<Servico> findByTurno(String turno);
	
	ArrayList<Servico> findByTipo(String tipo);

}