package polyana.example.poanoite.service;

import polyana.example.poanoite.dto.PostResponse;

import java.util.List;
import java.util.UUID;

public interface FeedStrategy {

    List<PostResponse> buscar(UUID usuarioId);
}