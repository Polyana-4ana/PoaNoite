package polyana.example.poanoite.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RepostRequest(

        @NotNull
        UUID usuarioId,

        @NotNull
        UUID postId

) {}