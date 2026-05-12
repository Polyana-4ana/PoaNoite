package polyana.example.poanoite.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "casas_de_festa")
public class CasaDeFesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Muitos locais de festa para um usuário (dono/administrador)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Usuario usuario;

    private String nome;

    private String tipoEvento; // ex: casamento, aniversário, formatura

    private String endereco;

    private Integer capacidade;

    private Double precoMedio;

    private Boolean estacionamento;

    private Boolean buffetIncluso;

    private String descricao;

    private Boolean status; // ativa / inativa

    private LocalDateTime dataCriacao;
}
