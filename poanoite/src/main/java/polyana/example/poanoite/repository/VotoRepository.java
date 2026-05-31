package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.Voto;
import polyana.example.poanoite.domain.VotoId;

import java.util.UUID;

public interface VotoRepository extends JpaRepository<Voto, VotoId> {

    boolean existsByUsuarioIdAndPostId(UUID usuarioId, UUID postId);

    void deleteByUsuarioId(UUID usuarioId);
}