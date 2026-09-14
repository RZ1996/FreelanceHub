package freelancehub.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Invoice {
    private Long id;
    private Client client;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal totalAmount;
    private LocalDate periodFrom;
    private LocalDate periodTo;






    enum Status{
        DRAFT,
        SENT,
        PAID,
        OVERDUE
    }
}
