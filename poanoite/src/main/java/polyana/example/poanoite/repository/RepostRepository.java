package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.Repost;

import java.util.UUID;

public interface RepostRepository extends JpaRepository<Repost, UUID> {

    void deleteByUsuarioId(UUID usuarioId);
}