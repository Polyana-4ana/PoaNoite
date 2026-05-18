package polyana.example.poanoite.controller;

import polyana.example.poanoite.service.CasaDeFestaService;
import polyana.example.poanoite.dto.LocalEventoCreateDTO;
import polyana.example.poanoite.dto.LocalEventoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/casas-de-festa")
public class CasaDeFestaController {

    private final CasaDeFestaService casaService;

    public CasaDeFestaController(CasaDeFestaService casaService) {
        this.casaService = casaService;
    }

    // LISTAR COM FILTROS (Specification)
    @GetMapping
    public List<LocalEventoResponseDTO> listarCasas(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String tipoEvento,
            @RequestParam(required = false) Integer capacidade,
            @RequestParam(required = false) Double precoMaximo,
            @RequestParam(required = false) Boolean estacionamento,
            @RequestParam(required = false) Boolean buffetIncluso,
            @RequestParam(required = false) Boolean status
    ) {
        return casaService.buscarComFiltros(
                nome,
                cidade,
                tipoEvento,
                capacidade,
                precoMaximo,
                estacionamento,
                buffetIncluso,
                status
        );
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public LocalEventoResponseDTO buscarPorId(@PathVariable Long id) {
        return casaService.buscarPorId(id);
    }

    // CRIAR
    @PostMapping
    public LocalEventoResponseDTO criar(@Valid @RequestBody LocalEventoCreateDTO dto) {
        return casaService.criar(dto);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public LocalEventoResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LocalEventoCreateDTO dto
    ) {
        return casaService.atualizar(id, dto);
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        casaService.deletar(id);
    }
}
