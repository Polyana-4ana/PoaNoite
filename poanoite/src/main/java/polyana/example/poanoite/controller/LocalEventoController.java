package polyana.example.poanoite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.dto.LocalEventoRequest;
import polyana.example.poanoite.dto.LocalEventoResponse;
import polyana.example.poanoite.service.LocalEventoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/locais")
@RequiredArgsConstructor
public class LocalEventoController {

    private final LocalEventoService service;

    @PostMapping
    public ResponseEntity<LocalEventoResponse> criar(
            @Valid @RequestBody LocalEventoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<LocalEventoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalEventoResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalEventoResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody LocalEventoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}