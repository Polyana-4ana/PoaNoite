package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import polyana.example.poanoite.domain.LocalEvento;

import java.util.List;
import java.util.UUID;

public interface LocalEventoRepository extends JpaRepository<LocalEvento, UUID> {

    @Query("SELECT l FROM LocalEvento l WHERE LOWER(l.nome) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(l.bairro) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<LocalEvento> pesquisarPorTermo(@Param("termo") String termo);

}