package polyana.example.poanoite.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class LocalEventoResponse {

    private UUID id;
    private String nome;
    private String endereco;
    private String bairro;
    private String cidade;
    private LocalDateTime criadoEm;
}