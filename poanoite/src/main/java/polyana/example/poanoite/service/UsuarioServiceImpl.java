package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polyana.example.poanoite.domain.Usuario;
import polyana.example.poanoite.dto.UsuarioRequest;
import polyana.example.poanoite.dto.UsuarioResponse;
import polyana.example.poanoite.exception.ResourceNotFoundException;
import polyana.example.poanoite.repository.UsuarioRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Override
    public UsuarioResponse criar(UsuarioRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email ja cadastrado");
        }

        if (repository.existsByCpfHash(hashSha256(request.getCpf()))) {
            throw new RuntimeException("CPF ja cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .cpfHash(hashSha256(request.getCpf()))
                .senhaHash(hashSha256(request.getSenha()))
                .build();

        Usuario salvo = repository.save(usuario);
        return toResponse(salvo);
    }

    @Override
    public UsuarioResponse buscarPorId(UUID id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario nao encontrado: " + id));
        return toResponse(usuario);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .fotoUrl(usuario.getFotoUrl())
                .criadoEm(usuario.getCriadoEm())
                .build();
    }

    private String hashSha256(String valor) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(valor.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash", e);
        }
    }
}