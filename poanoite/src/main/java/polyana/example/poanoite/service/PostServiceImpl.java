package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polyana.example.poanoite.domain.LocalEvento;
import polyana.example.poanoite.domain.Post;
import polyana.example.poanoite.domain.Usuario;
import polyana.example.poanoite.dto.PostRequest;
import polyana.example.poanoite.dto.PostResponse;
import polyana.example.poanoite.exception.ResourceNotFoundException;
import polyana.example.poanoite.repository.LocalEventoRepository;
import polyana.example.poanoite.repository.PostRepository;
import polyana.example.poanoite.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalEventoRepository localEventoRepository;

    @Override
    public PostResponse criar(UUID autorId, PostRequest request) {

        Usuario autor = usuarioRepository.findById(autorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario nao encontrado: " + autorId));

        LocalEvento local = localEventoRepository.findById(request.getLocalId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Local nao encontrado: " + request.getLocalId()));

        Post post = Post.builder()
                .autor(autor)
                .localEvento(local)
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .fotoUrl(request.getFotoUrl())
                .build();

        Post salvo = postRepository.save(post);
        return toResponse(salvo);
    }

    @Override
    public PostResponse buscarPorId(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post nao encontrado: " + id));
        return toResponse(post);
    }

    @Override
    public List<PostResponse> listarPorAutor(UUID autorId) {
        return postRepository.findByAutorIdOrderByCriadoEmDesc(autorId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public PostResponse atualizar(UUID id, UUID autorId, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post nao encontrado: " + id));

        if (!post.getAutor().getId().equals(autorId)) {
            throw new RuntimeException("Voce nao tem permissao para editar este post");
        }

        LocalEvento local = localEventoRepository.findById(request.getLocalId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Local nao encontrado: " + request.getLocalId()));

        post.setTitulo(request.getTitulo());
        post.setDescricao(request.getDescricao());
        post.setFotoUrl(request.getFotoUrl());
        post.setLocalEvento(local);

        Post atualizado = postRepository.save(post);
        return toResponse(atualizado);
    }

    @Override
    public void deletar(UUID id, UUID autorId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Post nao encontrado: " + id));

        if (!post.getAutor().getId().equals(autorId)) {
            throw new RuntimeException("Voce nao tem permissao para excluir este post");
        }

        postRepository.delete(post);
    }

    private PostResponse toResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .titulo(post.getTitulo())
                .descricao(post.getDescricao())
                .fotoUrl(post.getFotoUrl())
                .score(post.getScore())
                .criadoEm(post.getCriadoEm())
                .nomeAutor(post.getAutor().getNome())
                .nomeLocal(post.getLocalEvento() != null ? post.getLocalEvento().getNome() : null)
                .bairroLocal(post.getLocalEvento() != null ? post.getLocalEvento().getBairro() : null)
                .build();
    }
}