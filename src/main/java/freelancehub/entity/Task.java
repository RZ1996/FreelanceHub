package freelancehub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "project")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TASK_TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "TASK_DESC", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "ESTIMATED_HOURS")
    private Integer estimatedHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public enum Status {
        TODO,
        IN_PROGRESS,
        DONE
    }
}