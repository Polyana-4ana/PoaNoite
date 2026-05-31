package polyana.example.poanoite.service;

import polyana.example.poanoite.dto.UsuarioRequest;
import polyana.example.poanoite.dto.UsuarioResponse;

import java.util.UUID;

public interface UsuarioService {

    UsuarioResponse criar(UsuarioRequest request);

    UsuarioResponse buscarPorId(UUID id);

    void excluirConta(UUID usuarioId, String senha);
}