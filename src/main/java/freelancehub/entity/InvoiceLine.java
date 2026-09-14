package freelancehub.entity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class InvoiceLine {

    private Long id;
    private Invoice invoice;
    private Project project;
    private BigDecimal hours;
    private BigDecimal rate;
    private BigDecimal amount;

}
