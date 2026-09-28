package freelancehub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "client")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "INVOICE_DATE", nullable = false)
    private LocalDate issueDate;

    @Column(name = "INVOICE_DATE", nullable = false)
    private LocalDate dueDate;

    @Column(name = "INVOICE_AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "INVOICE_FROM", nullable = false)
    private LocalDate periodFrom;

    @Column(name = "INVOICE_TO", nullable = false)
    private LocalDate periodTo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        DRAFT,
        SENT,
        PAID,
        OVERDUE
    }
}