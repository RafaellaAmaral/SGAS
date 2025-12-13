package proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.model.DoacaoFinanceira;
import proj.repository.DoacaoFinanceiraRepository;

@Service
public class DoacaoFinanceiraService {

    @Autowired
    private DoacaoFinanceiraRepository doacaoFinanceiraRepository;

    public List<DoacaoFinanceira> listarTodos() {
        return doacaoFinanceiraRepository.findAll();
    }

    public DoacaoFinanceira buscarPeloId(Long id) {
        return doacaoFinanceiraRepository.findById(id).orElse(null);
    }

    public void inserir(DoacaoFinanceira doacao) {
        doacaoFinanceiraRepository.save(doacao);
    }

    public void alterar(DoacaoFinanceira doacao) {
        doacaoFinanceiraRepository.save(doacao);
    }

    public void excluir(Long id) {
        doacaoFinanceiraRepository.deleteById(id);
    }
}