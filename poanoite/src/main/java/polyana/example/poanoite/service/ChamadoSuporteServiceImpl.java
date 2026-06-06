package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polyana.example.poanoite.domain.ChamadoSuporte;
import polyana.example.poanoite.domain.Usuario;
import polyana.example.poanoite.dto.ChamadoSuporteRequest;
import polyana.example.poanoite.dto.ChamadoSuporteResponse;
import polyana.example.poanoite.exception.ResourceNotFoundException;
import polyana.example.poanoite.repository.ChamadoSuporteRepository;
import polyana.example.poanoite.repository.UsuarioRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChamadoSuporteServiceImpl
        implements ChamadoSuporteService {

    private final ChamadoSuporteRepository repository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public ChamadoSuporteResponse abrirChamado(
            ChamadoSuporteRequest request
    ) {

        Usuario usuario = usuarioRepository.findById(
                        request.usuarioId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario nao encontrado"));

        String protocolo =
                "SUP-" + UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

        ChamadoSuporte chamado = ChamadoSuporte.builder()
                .usuario(usuario)
                .titulo(request.titulo())
                .descricao(request.descricao())
                .protocolo(protocolo)
                .anexos(request.anexos())
                .build();

        ChamadoSuporte salvo = repository.save(chamado);

        return new ChamadoSuporteResponse(
                salvo.getId(),
                salvo.getProtocolo(),
                salvo.getTitulo(),
                salvo.getStatus(),
                salvo.getAnexos(),
                salvo.getCriadoEm()
        );
    }
}