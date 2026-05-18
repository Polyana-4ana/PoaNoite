package polyana.example.poanoite.service;

import polyana.example.poanoite.domain.LocalEvento;
import polyana.example.poanoite.repository.CasaDeFestaRepository;
import polyana.example.poanoite.repository.CasaDeFestaSpecification;
import polyana.example.poanoite.dto.LocalEventoCreateDTO;
import polyana.example.poanoite.dto.LocalEventoResponseDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasaDeFestaService {

    private final CasaDeFestaRepository casaRepository;

    public CasaDeFestaService(CasaDeFestaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    // CONVERSÃO ENTITY -> DTO
    private LocalEventoResponseDTO paraDTO(LocalEvento casa) {
        LocalEventoResponseDTO dto = new LocalEventoResponseDTO();

        dto.setId(casa.getId());
        dto.setNome(casa.getNome());
        dto.setTipoEvento(casa.getTipoEvento());
        dto.setEndereco(casa.getEndereco());
        dto.setCapacidade(casa.getCapacidade());
        dto.setPrecoMedio(casa.getPrecoMedio());
        dto.setEstacionamento(casa.getEstacionamento());
        dto.setBuffetIncluso(casa.getBuffetIncluso());
        dto.setStatus(casa.getStatus());

        return dto;
    }

    // CREATE
    public LocalEventoResponseDTO criar(LocalEventoCreateDTO dto) {

        LocalEvento casa = new LocalEvento();

        casa.setNome(dto.getNome());
        casa.setTipoEvento(dto.getTipoEvento());
        casa.setEndereco(dto.getEndereco());
        casa.setCapacidade(dto.getCapacidade());
        casa.setPrecoMedio(dto.getPrecoMedio());
        casa.setEstacionamento(dto.getEstacionamento());
        casa.setBuffetIncluso(dto.getBuffetIncluso());
        casa.setStatus(dto.getStatus());

        LocalEvento salvo = casaRepository.save(casa);

        return paraDTO(salvo);
    }

    // LISTAR TODOS
    public List<LocalEventoResponseDTO> listarTodos() {

        Specification<LocalEvento> specification = (root, query, cb) -> cb.conjunction();

        return casaRepository.findAll(specification)
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    // BUSCAR POR ID
    public LocalEventoResponseDTO buscarPorId(Long id) {
        LocalEvento casa = (LocalEvento) casaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Casa de festa não encontrada: " + id));

        return paraDTO(casa);
    }

    // FILTROS (Specification)
    public List<LocalEventoResponseDTO> buscarComFiltros(
            String nome,
            String cidade,
            String tipoEvento,
            Integer capacidade,
            Double precoMaximo,
            Boolean estacionamento,
            Boolean buffetIncluso,
            Boolean status
    ) {

        Specification<LocalEvento> specification = (root, query, cb) -> cb.conjunction();

        if (nome != null && !nome.isBlank()) {
            specification = specification.and(CasaDeFestaSpecification.temNome(nome));
        }

        if (cidade != null && !cidade.isBlank()) {
            specification = specification.and(CasaDeFestaSpecification.temCidade(cidade));
        }

        if (tipoEvento != null && !tipoEvento.isBlank()) {
            specification = specification.and(CasaDeFestaSpecification.temTipoEvento(tipoEvento));
        }

        if (capacidade != null) {
            specification = specification.and(CasaDeFestaSpecification.temCapacidade(capacidade));
        }

        if (precoMaximo != null) {
            specification = specification.and(CasaDeFestaSpecification.temPrecoMaximo(precoMaximo));
        }

        if (estacionamento != null) {
            specification = specification.and(CasaDeFestaSpecification.temEstacionamento(estacionamento));
        }

        if (buffetIncluso != null) {
            specification = specification.and(CasaDeFestaSpecification.temBuffetIncluso(buffetIncluso));
        }

        if (status != null) {
            specification = specification.and(CasaDeFestaSpecification.temStatus(status));
        }

        return casaRepository.findAll(specification)
                .stream()
                .map(this::paraDTO)
                .toList();
    }

    // UPDATE
    public LocalEventoResponseDTO atualizar(Long id, LocalEventoCreateDTO dto) {

        LocalEvento casa = (LocalEvento) casaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Casa de festa não encontrada: " + id));

        casa.setNome(dto.getNome());
        casa.setTipoEvento(dto.getTipoEvento());
        casa.setEndereco(dto.getEndereco());
        casa.setCapacidade(dto.getCapacidade());
        casa.setPrecoMedio(dto.getPrecoMedio());
        casa.setEstacionamento(dto.getEstacionamento());
        casa.setBuffetIncluso(dto.getBuffetIncluso());
        casa.setStatus(dto.getStatus());

        LocalEvento atualizado = casaRepository.save(casa);

        return paraDTO(atualizado);
    }

    // DELETE
    public void deletar(Long id) {

        LocalEvento casa = (LocalEvento) casaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Casa de festa não encontrada: " + id));

        casaRepository.delete(casa);
    }
}