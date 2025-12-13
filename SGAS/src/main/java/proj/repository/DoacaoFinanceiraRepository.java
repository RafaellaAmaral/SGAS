package proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proj.model.DoacaoFinanceira;

@Repository
public interface DoacaoFinanceiraRepository extends JpaRepository<DoacaoFinanceira, Long> {
    // Adicione métodos customizados se necessário, ex.:
    // List<DoacaoFinanceira> findByDoador(Usuario doador);
}