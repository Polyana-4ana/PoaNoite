package polyana.example.poanoite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcluirContaRequest {

    @NotBlank(message = "Senha e obrigatoria para confirmar exclusao")
    private String senha;
}