package polyana.example.poanoite.service;

import polyana.example.poanoite.dto.PostRequest;
import polyana.example.poanoite.dto.PostResponse;

import java.util.List;
import java.util.UUID;

public interface PostService {

    PostResponse criar(UUID autorId, PostRequest request);

    PostResponse buscarPorId(UUID id);

    List<PostResponse> listarPorAutor(UUID autorId);

    PostResponse atualizar(UUID id, UUID autorId, PostRequest request);

    void deletar(UUID id, UUID autorId);
}