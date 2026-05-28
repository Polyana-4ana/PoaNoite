package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polyana.example.poanoite.domain.Seguidor;
import polyana.example.poanoite.domain.SeguidorId;
import polyana.example.poanoite.domain.Usuario;
import polyana.example.poanoite.exception.ResourceNotFoundException;
import polyana.example.poanoite.repository.SeguidorRepository;
import polyana.example.poanoite.repository.UsuarioRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeguidorServiceImpl implements SeguidorService {

    private final SeguidorRepository seguidorRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public String toggleSeguir(UUID seguidorId, UUID seguidoId) {

        if (seguidorId.equals(seguidoId)) {
            throw new RuntimeException("Voce nao pode seguir a si mesmo");
        }

        Usuario seguidor = usuarioRepository.findById(seguidorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario nao encontrado: " + seguidorId));

        Usuario seguido = usuarioRepository.findById(seguidoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario nao encontrado: " + seguidoId));

        SeguidorId id = new SeguidorId(seguidorId, seguidoId);

        if (seguidorRepository.existsById(id)) {
            seguidorRepository.deleteById(id);
            return "Voce deixou de seguir " + seguido.getNome();
        }

        Seguidor novoSeguidor = Seguidor.builder()
                .seguidor(seguidor)
                .seguido(seguido)
                .build();

        seguidorRepository.save(novoSeguidor);
        return "Voce agora segue " + seguido.getNome();
    }
}