package freelancehub.dto;
import freelancehub.entity.Client;
import freelancehub.entity.Invoice;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceDTO {
    private Long id;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal totalAmount;
    private LocalDate periodFrom;
    private LocalDate periodTo;
    private Invoice.Status status;
}
