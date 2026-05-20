package polyana.example.poanoite.service;

import polyana.example.poanoite.dto.LocalEventoRequest;
import polyana.example.poanoite.dto.LocalEventoResponse;

import java.util.List;
import java.util.UUID;

public interface LocalEventoService {

    LocalEventoResponse criar(LocalEventoRequest request);

    List<LocalEventoResponse> listarTodos();

    LocalEventoResponse buscarPorId(UUID id);

    LocalEventoResponse atualizar(UUID id, LocalEventoRequest request);

    void deletar(UUID id);
}