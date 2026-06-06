package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import polyana.example.poanoite.dto.PostResponse;
import polyana.example.poanoite.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component("explorar")
@RequiredArgsConstructor
public class ExploreFeedStrategy implements FeedStrategy {

    private final PostRepository postRepository;

    @Override
    public List<PostResponse> buscar(UUID usuarioId) {
        LocalDateTime inicioDia = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime fimDia    = inicioDia.plusDays(1);

        return postRepository
                .findBycriadoEmBetweenOrderByScoreDesc(inicioDia, fimDia)
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