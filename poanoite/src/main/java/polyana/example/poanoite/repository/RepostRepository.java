package polyana.example.poanoite.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.Repost;

import java.util.UUID;

public interface RepostRepository extends JpaRepository<Repost, UUID> {

    @Transactional
    void deleteByUsuarioId(UUID usuarioId);
}