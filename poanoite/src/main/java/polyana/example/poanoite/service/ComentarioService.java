package polyana.example.poanoite.service;

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
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostRepository postRepository;

    public ComentarioService(
            ComentarioRepository comentarioRepository,
            UsuarioRepository usuarioRepository,
            PostRepository postRepository
    ) {
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.postRepository = postRepository;
    }

    public ComentarioResponse criarComentario(ComentarioRequest request){

        Usuario usuario = usuarioRepository.findById(request.autorId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));

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