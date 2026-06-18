package polyana.example.poanoite.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PesquisaResponse {

    private List<PostResponse> posts;
    private List<UsuarioResponse> usuarios;
    private List<LocalEventoResponse> locais;
}