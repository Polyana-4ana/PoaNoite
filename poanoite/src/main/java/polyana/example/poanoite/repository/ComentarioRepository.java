package polyana.example.poanoite.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.Comentario;

import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {

    @Transactional
    void deleteByAutorId(UUID autorId);
}