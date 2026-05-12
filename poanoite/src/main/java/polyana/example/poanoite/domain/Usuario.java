package polyana.example.poanoite.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String email;
    private String senhaHash;
    private String cidade;
    private String estado;
    private LocalDateTime dataCriacao;
}
