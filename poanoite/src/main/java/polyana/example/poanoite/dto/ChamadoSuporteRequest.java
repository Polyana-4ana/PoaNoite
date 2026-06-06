package polyana.example.poanoite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record ChamadoSuporteRequest(

        @NotNull
        UUID usuarioId,

        @NotBlank
        String titulo,

        @NotBlank
        String descricao,

        List<String> anexos

) {}