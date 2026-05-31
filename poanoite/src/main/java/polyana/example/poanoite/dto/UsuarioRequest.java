package polyana.example.poanoite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank(message = "Nome e obrigatorio")
    @Size(max = 100, message = "Nome deve ter no maximo 100 caracteres")
    private String nome;

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Telefone e obrigatorio")
    private String telefone;

    @NotBlank(message = "CPF e obrigatorio")
    @Size(min = 11, max = 11, message = "CPF deve ter exatamente 11 digitos")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas numeros")
    private String cpf;

    @NotBlank(message = "Senha e obrigatoria")
    @Size(min = 8, message = "Senha deve ter no minimo 8 caracteres")
    @Pattern(regexp = ".*[A-Z].*", message = "Senha deve ter ao menos uma letra maiuscula")
    @Pattern(regexp = ".*[0-9].*", message = "Senha deve ter ao menos um numero")
    private String senha;

    private String fotoUrl;
}