package polyana.example.poanoite.domain;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SeguidorId implements Serializable {

    private UUID seguidor;
    private UUID seguido;
}