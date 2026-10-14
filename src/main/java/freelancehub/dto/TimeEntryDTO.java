package freelancehub.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TimeEntryDTO {

    private Long id;
    @NotBlank
    @Size(max = 100, message = "For title is maximum 100 characters")
    private String title;

    @NotNull
    private LocalDate date;

    @NotNull
    @Positive
    @DecimalMax(value = "24.00", message = "Hours can not be more than 24")
    private BigDecimal hours;

    @Size(max = 500, message = "For note is maximum 500 characters")
    private String note;
}
