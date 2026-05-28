package polyana.example.poanoite.service;

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
public class RepostService {

    private final RepostRepository repostRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostRepository postRepository;

    public RepostService(
            RepostRepository repostRepository,
            UsuarioRepository usuarioRepository,
            PostRepository postRepository
    ) {
        this.repostRepository = repostRepository;
        this.usuarioRepository = usuarioRepository;
        this.postRepository = postRepository;
    }

    public RepostResponse repostar(RepostRequest request){

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));

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