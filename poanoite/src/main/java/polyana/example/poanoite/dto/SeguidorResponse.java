package polyana.example.poanoite.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class SeguidorResponse {

    private UUID seguidorId;
    private String nomeSegUidor;
    private UUID seguidoId;
    private String nomeSeguido;
    private LocalDateTime criadoEm;
}