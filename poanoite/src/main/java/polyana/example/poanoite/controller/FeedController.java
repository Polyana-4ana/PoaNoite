package polyana.example.poanoite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.dto.PostResponse;
import polyana.example.poanoite.service.FeedStrategy;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final Map<String, FeedStrategy> estrategias;

    @GetMapping
    public ResponseEntity<List<PostResponse>> feed(
            @RequestHeader("X-User-Id") UUID usuarioId,
            @RequestParam(defaultValue = "explorar") String tipo) {

        FeedStrategy estrategia = estrategias.get(tipo);

        if (estrategia == null) {
            throw new RuntimeException("Tipo de feed invalido: " + tipo);
        }

        return ResponseEntity.ok(estrategia.buscar(usuarioId));
    }
}