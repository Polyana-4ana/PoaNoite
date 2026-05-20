package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polyana.example.poanoite.domain.LocalEvento;
import polyana.example.poanoite.dto.LocalEventoRequest;
import polyana.example.poanoite.dto.LocalEventoResponse;
import polyana.example.poanoite.exception.ResourceNotFoundException;
import polyana.example.poanoite.repository.LocalEventoRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalEventoServiceImpl implements LocalEventoService {

    private final LocalEventoRepository repository;

    @Override
    public LocalEventoResponse criar(LocalEventoRequest request) {
        LocalEvento local = LocalEvento.builder()
                .nome(request.getNome())
                .endereco(request.getEndereco())
                .bairro(request.getBairro())
                .cidade(request.getCidade() != null ? request.getCidade() : "Porto Alegre")
                .build();

        LocalEvento salvo = repository.save(local);
        return toResponse(salvo);
    }

    @Override
    public List<LocalEventoResponse> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public LocalEventoResponse buscarPorId(UUID id) {
        LocalEvento local = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Local de evento nao encontrado: " + id));
        return toResponse(local);
    }

    @Override
    public LocalEventoResponse atualizar(UUID id, LocalEventoRequest request) {
        LocalEvento local = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Local de evento nao encontrado: " + id));

        local.setNome(request.getNome());
        local.setEndereco(request.getEndereco());
        local.setBairro(request.getBairro());
        if (request.getCidade() != null) {
            local.setCidade(request.getCidade());
        }

        LocalEvento atualizado = repository.save(local);
        return toResponse(atualizado);
    }

    @Override
    public void deletar(UUID id) {
        LocalEvento local = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Local de evento nao encontrado: " + id));
        repository.delete(local);
    }

    private LocalEventoResponse toResponse(LocalEvento local) {
        return LocalEventoResponse.builder()
                .id(local.getId())
                .nome(local.getNome())
                .endereco(local.getEndereco())
                .bairro(local.getBairro())
                .cidade(local.getCidade())
                .criadoEm(local.getCriadoEm())
                .build();
    }
}