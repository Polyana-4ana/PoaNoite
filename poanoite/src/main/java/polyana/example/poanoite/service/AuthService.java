package polyana.example.poanoite.service;

import polyana.example.poanoite.domain.Usuario;
import polyana.example.poanoite.dto.LoginRequest;
import polyana.example.poanoite.dto.LoginResponse;
import polyana.example.poanoite.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenhaHash())) {
            throw new RuntimeException("Senha inválida");
        }

        return new LoginResponse(
                usuario.getNome(),
                usuario.getEmail(),
                "Login realizado com sucesso"
        );
    }
}