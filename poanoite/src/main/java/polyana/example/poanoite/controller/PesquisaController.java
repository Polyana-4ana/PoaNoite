package polyana.example.poanoite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.dto.PesquisaResponse;
import polyana.example.poanoite.service.PesquisaService;

@RestController
@RequestMapping("/pesquisa")
@RequiredArgsConstructor
public class PesquisaController {

    private final PesquisaService service;

    @GetMapping
    public ResponseEntity<PesquisaResponse> pesquisar(
            @RequestParam String q) {
        return ResponseEntity.ok(service.pesquisar(q));
    }
}