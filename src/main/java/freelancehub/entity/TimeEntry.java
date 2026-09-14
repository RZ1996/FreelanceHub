package freelancehub.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class TimeEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long title;
    private Task task;
    private User user;
    private LocalDate date;
    private BigDecimal hours;
    private String note;
}
