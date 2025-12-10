package proj.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proj.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
	
	Servico findByTitulo(String titulo); //Tem que ter indice unico na tabela
	
	ArrayList<Servico> findByTurno(String turno);
	
	ArrayList<Servico> findByTipo(String tipo);

}