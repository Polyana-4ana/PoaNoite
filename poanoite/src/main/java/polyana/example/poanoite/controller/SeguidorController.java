package polyana.example.poanoite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.service.SeguidorService;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class SeguidorController {

    private final SeguidorService service;

    @PostMapping("/{seguidoId}/seguir")
    public ResponseEntity<String> toggleSeguir(
            @RequestHeader("X-User-Id") UUID seguidorId,
            @PathVariable UUID seguidoId) {
        String mensagem = service.toggleSeguir(seguidorId, seguidoId);
        return ResponseEntity.ok(mensagem);
    }
}