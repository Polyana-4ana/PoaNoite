package polyana.example.poanoite.controller;

import polyana.example.poanoite.service.CasaDeFestaService;
import polyana.example.poanoite.dto.CasaDeFestaCreateDTO;
import polyana.example.poanoite.dto.CasaDeFestaResponseDTO;
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
    public List<CasaDeFestaResponseDTO> listarCasas(
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
    public CasaDeFestaResponseDTO buscarPorId(@PathVariable Long id) {
        return casaService.buscarPorId(id);
    }

    // CRIAR
    @PostMapping
    public CasaDeFestaResponseDTO criar(@Valid @RequestBody CasaDeFestaCreateDTO dto) {
        return casaService.criar(dto);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public CasaDeFestaResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CasaDeFestaCreateDTO dto
    ) {
        return casaService.atualizar(id, dto);
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        casaService.deletar(id);
    }
}
