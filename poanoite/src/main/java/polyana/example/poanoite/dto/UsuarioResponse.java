package polyana.example.poanoite.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class UsuarioResponse {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String fotoUrl;
    private LocalDateTime criadoEm;
}