package polyana.example.poanoite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polyana.example.poanoite.dto.ChamadoSuporteRequest;
import polyana.example.poanoite.dto.ChamadoSuporteResponse;
import polyana.example.poanoite.service.ChamadoSuporteService;

@RestController
@RequestMapping("/suporte")
@RequiredArgsConstructor
public class ChamadoSuporteController {

    private final ChamadoSuporteService service;

    @PostMapping
    public ResponseEntity<ChamadoSuporteResponse> abrirChamado(
            @Valid @RequestBody ChamadoSuporteRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.abrirChamado(request));
    }
}