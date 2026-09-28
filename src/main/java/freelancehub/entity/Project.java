package freelancehub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "client")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PROJECT_NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "PROJECT_DESC", length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "HOURLY_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal hourlyRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        ACTIVE,
        COMPLETED,
        ON_HOLD
    }
}