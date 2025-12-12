package proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.model.Servico;
import proj.repository.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Servico buscarPeloId(Long id) {
        return servicoRepository.findById(id).orElse(null);
    }

    public void inserir(Servico servico) {
        servicoRepository.save(servico);
    }

    public void alterar(Servico servico) {
        servicoRepository.save(servico);
    }

    public void excluir(Long id) {
        servicoRepository.deleteById(id);
    }

    // Métodos adicionais baseados no ServicoRepository (se necessário)
    public Servico buscarPorTitulo(String titulo) {
        return servicoRepository.findByTitulo(titulo);
    }

    public List<Servico> listarPorTurno(String turno) {
        return servicoRepository.findByTurno(turno);
    }

    public List<Servico> listarPorTipo(String tipo) {
        return servicoRepository.findByTipo(tipo);
    }
}