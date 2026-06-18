package polyana.example.poanoite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polyana.example.poanoite.dto.LocalEventoResponse;
import polyana.example.poanoite.dto.PesquisaResponse;
import polyana.example.poanoite.dto.PostResponse;
import polyana.example.poanoite.dto.UsuarioResponse;
import polyana.example.poanoite.repository.LocalEventoRepository;
import polyana.example.poanoite.repository.PostRepository;
import polyana.example.poanoite.repository.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PesquisaServiceImpl implements PesquisaService {

    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalEventoRepository localEventoRepository;

    @Override
    public PesquisaResponse pesquisar(String termo) {

        if (termo == null || termo.trim().length() < 2) {
            return PesquisaResponse.builder()
                    .posts(List.of())
                    .usuarios(List.of())
                    .locais(List.of())
                    .build();
        }

        List<PostResponse> posts = postRepository
                .pesquisarPorTermo(termo)
                .stream()
                .map(p -> PostResponse.builder()
                        .id(p.getId())
                        .titulo(p.getTitulo())
                        .descricao(p.getDescricao())
                        .fotoUrl(p.getFotoUrl())
                        .score(p.getScore())
                        .criadoEm(p.getCriadoEm())
                        .nomeAutor(p.getAutor().getNome())
                        .nomeLocal(p.getLocalEvento() != null
                                ? p.getLocalEvento().getNome() : null)
                        .bairroLocal(p.getLocalEvento() != null
                                ? p.getLocalEvento().getBairro() : null)
                        .build())
                .toList();

        List<UsuarioResponse> usuarios = usuarioRepository
                .pesquisarPorNome(termo)
                .stream()
                .map(u -> UsuarioResponse.builder()
                        .id(u.getId())
                        .nome(u.getNome())
                        .email(u.getEmail())
                        .telefone(u.getTelefone())
                        .fotoUrl(u.getFotoUrl())
                        .criadoEm(u.getCriadoEm())
                        .build())
                .toList();

        List<LocalEventoResponse> locais = localEventoRepository
                .pesquisarPorTermo(termo)
                .stream()
                .map(l -> LocalEventoResponse.builder()
                        .id(l.getId())
                        .nome(l.getNome())
                        .endereco(l.getEndereco())
                        .bairro(l.getBairro())
                        .cidade(l.getCidade())
                        .criadoEm(l.getCriadoEm())
                        .build())
                .toList();

        return PesquisaResponse.builder()
                .posts(posts)
                .usuarios(usuarios)
                .locais(locais)
                .build();
    }
}