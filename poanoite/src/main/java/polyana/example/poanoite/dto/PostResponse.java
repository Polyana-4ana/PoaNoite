package polyana.example.poanoite.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class PostResponse {

    private UUID id;
    private String titulo;
    private String descricao;
    private String fotoUrl;
    private Integer score;
    private LocalDateTime criadoEm;
    private String nomeAutor;
    private String nomeLocal;
    private String bairroLocal;
}