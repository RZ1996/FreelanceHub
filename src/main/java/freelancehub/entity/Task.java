package freelancehub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long title;
    private String description;
    private Project.Status status;
    private Integer estimatedHours;
    @Enumerated(EnumType.STRING)

    private Project project;



    enum Status{
        TODO,
        IN_PROGRESS,
        DONE
    }
}
