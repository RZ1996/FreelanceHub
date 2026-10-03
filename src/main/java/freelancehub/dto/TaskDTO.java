package freelancehub.dto;
import freelancehub.entity.Project;
import freelancehub.entity.Task;
import lombok.Data;

@Data
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private Task.Status status;
    private Integer estimatedHours;
}
