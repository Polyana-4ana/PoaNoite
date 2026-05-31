package polyana.example.poanoite.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record RepostResponse(

        UUID repostId,
        String usuario,
        UUID postId,
        LocalDateTime criadoEm

) {}