package polyana.example.poanoite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalEventoRequest {

    @NotBlank(message = "Nome e obrigatorio")
    @Size(max = 150, message = "Nome deve ter no maximo 150 caracteres")
    private String nome;

    @NotBlank(message = "Endereco e obrigatorio")
    @Size(max = 255, message = "Endereco deve ter no maximo 255 caracteres")
    private String endereco;

    @Size(max = 100, message = "Bairro deve ter no maximo 100 caracteres")
    private String bairro;

    @Size(max = 100, message = "Cidade deve ter no maximo 100 caracteres")
    private String cidade;
}