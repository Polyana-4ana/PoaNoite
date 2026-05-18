package polyana.example.poanoite.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chamado_suporte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChamadoSuporte {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, unique = true, length = 50)
    private String protocolo;

    @Column(nullable = false, length = 30)
    @Builder.Default
    private String status = "ABERTO";

    @ElementCollection
    @CollectionTable(name = "chamado_suporte_anexos", joinColumns = @JoinColumn(name = "chamado_id"))
    @Column(name = "url")
    @Builder.Default
    private List<String> anexos = new ArrayList<>();

    @Column(name = "criado_em", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime criadoEm = LocalDateTime.now();
}