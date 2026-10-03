package freelancehub.dto;
import freelancehub.entity.Task;
import freelancehub.entity.User;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TimeEntryDTO {

    private Long id;
    private String title;
    private LocalDate date;
    private BigDecimal hours;
    private String note;
}
