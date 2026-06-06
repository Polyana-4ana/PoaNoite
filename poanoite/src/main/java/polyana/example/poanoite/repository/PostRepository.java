package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import polyana.example.poanoite.domain.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findBycriadoEmBetweenOrderByScoreDesc(
            LocalDateTime inicio,
            LocalDateTime fim
    );

    @Query("SELECT p FROM Post p WHERE p.autor.id IN " +
            "(SELECT s.seguido.id FROM Seguidor s WHERE s.seguidor.id = :usuarioId) " +
            "ORDER BY p.criadoEm DESC")
    List<Post> findPostsDeSeguidosPor(@Param("usuarioId") UUID usuarioId);

    List<Post> findByAutorIdOrderByCriadoEmDesc(UUID autorId);

    @Query("SELECT p FROM Post p WHERE LOWER(p.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(p.descricao) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Post> pesquisarPorTermo(@Param("termo") String termo);

    void deleteByAutorId(UUID autorId);

}