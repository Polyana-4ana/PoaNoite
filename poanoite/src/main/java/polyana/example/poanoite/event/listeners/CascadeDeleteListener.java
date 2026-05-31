package polyana.example.poanoite.event.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import polyana.example.poanoite.event.ContaExcluidaEvent;
import polyana.example.poanoite.repository.ComentarioRepository;
import polyana.example.poanoite.repository.PostRepository;
import polyana.example.poanoite.repository.RepostRepository;
import polyana.example.poanoite.repository.SeguidorRepository;
import polyana.example.poanoite.repository.VotoRepository;

@Component
@RequiredArgsConstructor
public class CascadeDeleteListener {

    private final PostRepository postRepository;
    private final ComentarioRepository comentarioRepository;
    private final VotoRepository votoRepository;
    private final RepostRepository repostRepository;
    private final SeguidorRepository seguidorRepository;

    @EventListener
    public void onContaExcluida(ContaExcluidaEvent event) {
        postRepository.deleteByAutorId(event.getUsuarioId());
        comentarioRepository.deleteByAutorId(event.getUsuarioId());
        votoRepository.deleteByUsuarioId(event.getUsuarioId());
        repostRepository.deleteByUsuarioId(event.getUsuarioId());
        seguidorRepository.deleteBySeguidorId(event.getUsuarioId());
        seguidorRepository.deleteBySeguidoId(event.getUsuarioId());
    }
}