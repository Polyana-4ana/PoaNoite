package polyana.example.poanoite.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ChamadoSuporteResponse(

        UUID id,
        String protocolo,
        String titulo,
        String status,
        List<String> anexos,
        LocalDateTime criadoEm

) {}