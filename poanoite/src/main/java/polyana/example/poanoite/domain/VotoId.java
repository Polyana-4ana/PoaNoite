package polyana.example.poanoite.domain;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VotoId implements Serializable {

    private UUID usuario;
    private UUID post;
}