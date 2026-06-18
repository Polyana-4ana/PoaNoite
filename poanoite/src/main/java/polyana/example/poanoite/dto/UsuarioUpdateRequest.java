package polyana.example.poanoite.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioUpdateRequest(

        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        String fotoUrl

) {}