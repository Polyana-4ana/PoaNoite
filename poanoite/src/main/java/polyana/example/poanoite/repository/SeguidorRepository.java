package polyana.example.poanoite.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.Seguidor;
import polyana.example.poanoite.domain.SeguidorId;

import java.util.UUID;

public interface SeguidorRepository extends JpaRepository<Seguidor, SeguidorId> {

    boolean existsBySeguidorIdAndSeguidoId(UUID seguidorId, UUID seguidoId);

    void deleteBySeguidorIdAndSeguidoId(UUID seguidorId, UUID seguidoId);

    @Transactional
    void deleteBySeguidorId(UUID seguidorId);

    @Transactional
    void deleteBySeguidoId(UUID seguidoId);

}