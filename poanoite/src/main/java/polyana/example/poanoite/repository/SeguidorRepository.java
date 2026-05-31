package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.Seguidor;
import polyana.example.poanoite.domain.SeguidorId;

import java.util.UUID;

public interface SeguidorRepository extends JpaRepository<Seguidor, SeguidorId> {

    boolean existsBySeguidorIdAndSeguidoId(UUID seguidorId, UUID seguidoId);

    void deleteBySeguidorIdAndSeguidoId(UUID seguidorId, UUID seguidoId);

    void deleteBySeguidorId(UUID seguidorId);

    void deleteBySeguidoId(UUID seguidoId);

}