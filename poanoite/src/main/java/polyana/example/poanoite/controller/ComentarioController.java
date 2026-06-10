package polyana.example.poanoite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.dto.ComentarioRequest;
import polyana.example.poanoite.dto.ComentarioResponse;
import polyana.example.poanoite.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService service;

    @PostMapping
    public ResponseEntity<ComentarioResponse> criar(
            @Valid @RequestBody ComentarioRequest request) {
        return ResponseEntity.ok(service.criarComentario(request));
    }
}
