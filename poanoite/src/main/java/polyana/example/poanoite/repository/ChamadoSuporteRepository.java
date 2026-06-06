package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.ChamadoSuporte;

import java.util.UUID;

public interface ChamadoSuporteRepository
        extends JpaRepository<ChamadoSuporte, UUID> {

    boolean existsByProtocolo(String protocolo);

}