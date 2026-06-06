package polyana.example.poanoite.service;

import java.util.UUID;

public interface VotoService {

    String toggleVoto(UUID usuarioId, UUID postId);
}