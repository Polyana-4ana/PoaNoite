package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import polyana.example.poanoite.dto.PostResponse;
import polyana.example.poanoite.repository.PostRepository;

import java.util.List;
import java.util.UUID;

@Component("seguindo")
@RequiredArgsConstructor
public class FollowingFeedStrategy implements FeedStrategy {

    private final PostRepository postRepository;

    @Override
    public List<PostResponse> buscar(UUID usuarioId) {
        return postRepository
                .findPostsDeSeguidosPor(usuarioId)
                .stream()
                .map(p -> PostResponse.builder()
                        .id(p.getId())
                        .titulo(p.getTitulo())
                        .descricao(p.getDescricao())
                        .fotoUrl(p.getFotoUrl())
                        .score(p.getScore())
                        .criadoEm(p.getCriadoEm())
                        .nomeAutor(p.getAutor().getNome())
                        .nomeLocal(p.getLocalEvento() != null ? p.getLocalEvento().getNome() : null)
                        .bairroLocal(p.getLocalEvento() != null ? p.getLocalEvento().getBairro() : null)
                        .build())
                .toList();
    }
}