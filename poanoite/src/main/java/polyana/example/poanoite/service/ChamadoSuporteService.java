package polyana.example.poanoite.service;

import polyana.example.poanoite.dto.ChamadoSuporteRequest;
import polyana.example.poanoite.dto.ChamadoSuporteResponse;

public interface ChamadoSuporteService {

    ChamadoSuporteResponse abrirChamado(
            ChamadoSuporteRequest request
    );
}