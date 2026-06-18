package polyana.example.poanoite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.service.VotoService;

import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class VotoController {

    private final VotoService service;

    @PostMapping("/{postId}/votos")
    public ResponseEntity<String> toggleVoto(
            @RequestHeader("X-User-Id") UUID usuarioId,
            @PathVariable UUID postId) {
        return ResponseEntity.ok(service.toggleVoto(usuarioId, postId));
    }
}