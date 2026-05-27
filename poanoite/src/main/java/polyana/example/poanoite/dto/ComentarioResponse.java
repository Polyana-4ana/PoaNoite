package polyana.example.poanoite.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ComentarioResponse(

        UUID id,
        String autor,
        String conteudo,
        LocalDateTime criadoEm

) {}