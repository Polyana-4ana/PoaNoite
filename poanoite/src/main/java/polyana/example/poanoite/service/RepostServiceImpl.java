package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polyana.example.poanoite.domain.Post;
import polyana.example.poanoite.domain.Repost;
import polyana.example.poanoite.domain.Usuario;
import polyana.example.poanoite.dto.RepostRequest;
import polyana.example.poanoite.dto.RepostResponse;
import polyana.example.poanoite.repository.PostRepository;
import polyana.example.poanoite.repository.RepostRepository;
import polyana.example.poanoite.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class RepostServiceImpl implements RepostService {

    private final RepostRepository repostRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostRepository postRepository;

    @Override
    public RepostResponse repostar(RepostRequest request) {

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new RuntimeException("Post nao encontrado"));

        Repost repost = Repost.builder()
                .usuario(usuario)
                .postOriginal(post)
                .build();

        Repost salvo = repostRepository.save(repost);

        return new RepostResponse(
                salvo.getId(),
                usuario.getNome(),
                post.getId(),
                salvo.getCriadoEm()
        );
    }
}