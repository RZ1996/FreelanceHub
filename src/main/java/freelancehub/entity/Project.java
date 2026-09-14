package freelancehub.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Client client;
    private BigDecimal hourlyRate;

    @Enumerated(EnumType.STRING)
    private Status status;


    enum Status{
        ACTIVE,
        COMPLETED,
        ON_HOLD
    }

}

