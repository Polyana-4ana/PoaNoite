package polyana.example.poanoite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ComentarioRequest(

        @NotNull
        UUID postId,

        @NotNull
        UUID autorId,

        @NotBlank
        String conteudo

) {}