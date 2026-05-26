package polyana.example.poanoite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.dto.PostRequest;
import polyana.example.poanoite.dto.PostResponse;
import polyana.example.poanoite.service.PostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @PostMapping
    public ResponseEntity<PostResponse> criar(
            @RequestHeader("X-User-Id") UUID autorId,
            @Valid @RequestBody PostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(autorId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/usuario/{autorId}")
    public ResponseEntity<List<PostResponse>> listarPorAutor(@PathVariable UUID autorId) {
        return ResponseEntity.ok(service.listarPorAutor(autorId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponse> atualizar(
            @PathVariable UUID id,
            @RequestHeader("X-User-Id") UUID autorId,
            @Valid @RequestBody PostRequest request) {
        return ResponseEntity.ok(service.atualizar(id, autorId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable UUID id,
            @RequestHeader("X-User-Id") UUID autorId) {
        service.deletar(id, autorId);
        return ResponseEntity.noContent().build();
    }
}