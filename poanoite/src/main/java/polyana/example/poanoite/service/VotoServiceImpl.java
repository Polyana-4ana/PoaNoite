package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polyana.example.poanoite.domain.Post;
import polyana.example.poanoite.domain.Usuario;
import polyana.example.poanoite.domain.Voto;
import polyana.example.poanoite.domain.VotoId;
import polyana.example.poanoite.exception.ResourceNotFoundException;
import polyana.example.poanoite.repository.PostRepository;
import polyana.example.poanoite.repository.UsuarioRepository;
import polyana.example.poanoite.repository.VotoRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public String toggleVoto(UUID usuarioId, UUID postId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario nao encontrado: " + usuarioId));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post nao encontrado: " + postId));

        VotoId id = new VotoId(usuarioId, postId);

        if (votoRepository.existsById(id)) {
            votoRepository.deleteById(id);
            post.setScore(post.getScore() - 1);
            postRepository.save(post);
            return "Voto removido";
        }

        Voto voto = Voto.builder()
                .usuario(usuario)
                .post(post)
                .build();

        votoRepository.save(voto);
        post.setScore(post.getScore() + 1);
        postRepository.save(post);
        return "Voto registrado";
    }
}