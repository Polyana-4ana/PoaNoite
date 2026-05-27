package polyana.example.poanoite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polyana.example.poanoite.domain.Post;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}