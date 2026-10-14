package freelancehub.dto;
import freelancehub.entity.Invoice;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceDTO {
    private Long id;

    @NotNull
    private LocalDate issueDate;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    @Positive
    @Digits(integer = 8, fraction = 2, message = "Total amount can have max 8 digits and 2 decimals")
    private BigDecimal totalAmount;

    @NotNull
    private LocalDate periodFrom;

    @NotNull
    private LocalDate periodTo;

    @NotNull
    private Invoice.Status status;
}