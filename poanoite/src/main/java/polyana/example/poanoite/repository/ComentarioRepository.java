package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.Comentario;

import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {

    void deleteByAutorId(UUID autorId);
}