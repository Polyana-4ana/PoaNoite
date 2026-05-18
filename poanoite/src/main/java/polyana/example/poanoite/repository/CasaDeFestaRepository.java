package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import polyana.example.poanoite.domain.LocalEvento;

public interface CasaDeFestaRepository extends JpaRepository<LocalEvento, Long>,
        JpaSpecificationExecutor<LocalEvento> {
}