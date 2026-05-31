package polyana.example.poanoite.event;

import java.util.UUID;

public class ContaExcluidaEvent {

    private final UUID usuarioId;

    public ContaExcluidaEvent(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }
}