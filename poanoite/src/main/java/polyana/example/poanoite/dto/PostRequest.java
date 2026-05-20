package polyana.example.poanoite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostRequest {

    @NotBlank(message = "Titulo e obrigatorio")
    @Size(max = 200, message = "Titulo deve ter no maximo 200 caracteres")
    private String titulo;

    @NotBlank(message = "Descricao e obrigatoria")
    private String descricao;

    @NotNull(message = "Local e obrigatorio")
    private UUID localId;

    private String fotoUrl;
}