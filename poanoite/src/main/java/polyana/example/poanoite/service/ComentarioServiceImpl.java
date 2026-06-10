package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polyana.example.poanoite.domain.Comentario;
import polyana.example.poanoite.domain.Post;
import polyana.example.poanoite.domain.Usuario;
import polyana.example.poanoite.dto.ComentarioRequest;
import polyana.example.poanoite.dto.ComentarioResponse;
import polyana.example.poanoite.repository.ComentarioRepository;
import polyana.example.poanoite.repository.PostRepository;
import polyana.example.poanoite.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostRepository postRepository;

    @Override
    public ComentarioResponse criarComentario(ComentarioRequest request) {

        Usuario usuario = usuarioRepository.findById(request.autorId())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new RuntimeException("Post nao encontrado"));

        Comentario comentario = Comentario.builder()
                .autor(usuario)
                .post(post)
                .conteudo(request.conteudo())
                .build();

        Comentario salvo = comentarioRepository.save(comentario);

        return new ComentarioResponse(
                salvo.getId(),
                salvo.getAutor().getNome(),
                salvo.getConteudo(),
                salvo.getCriadoEm()
        );
    }
}