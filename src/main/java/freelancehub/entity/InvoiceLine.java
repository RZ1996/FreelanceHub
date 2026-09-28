package freelancehub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"invoice", "project"})
public class InvoiceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "INVOICE_LINE_HOURS", precision = 10, scale = 2, nullable = false)
    private BigDecimal hours;

    @Column(name = "INVOICE_LINE_RATE", precision = 10, scale = 2, nullable = false)
    private BigDecimal rate;

    @Column(name = "INVOICE_LINE_AMOUNT", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;
}