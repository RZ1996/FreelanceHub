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
@ToString(exclude = {"task", "user"})
public class TimeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TIME_ENTRY_NAME", length = 100, nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "ENTRY_DATE", nullable = false)
    private LocalDate date;

    @Column(name = "HOURS", precision = 5, scale = 2, nullable = false)
    private BigDecimal hours;

    @Column(name = "NOTE", length = 500)
    private String note;
}