package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import polyana.example.poanoite.domain.CasaDeFesta;

public interface CasaDeFestaRepository extends JpaRepository<CasaDeFesta, Long>,
        JpaSpecificationExecutor<CasaDeFesta> {
}