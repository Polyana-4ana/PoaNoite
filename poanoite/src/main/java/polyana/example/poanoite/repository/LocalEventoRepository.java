package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.LocalEvento;

import java.util.UUID;

public interface LocalEventoRepository extends JpaRepository<LocalEvento, UUID> {

}