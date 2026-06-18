package polyana.example.poanoite.dto;

import java.util.UUID;

public record LoginResponse(
        UUID id,
        String nome,
        String email,
        String mensagem
) {}