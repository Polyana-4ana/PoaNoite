package polyana.example.poanoite.service;

import polyana.example.poanoite.dto.ComentarioRequest;
import polyana.example.poanoite.dto.ComentarioResponse;

public interface ComentarioService {

    ComentarioResponse criarComentario(ComentarioRequest request);
}