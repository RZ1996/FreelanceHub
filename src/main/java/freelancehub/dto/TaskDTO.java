package freelancehub.dto;
import freelancehub.entity.Project;
import freelancehub.entity.Task;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TaskDTO {

    private Long id;
    @NotBlank
    @Size(max = 100, message = "For title is maximum 100 characters")
    private String title;

    @Size(max = 500, message = "For description is maximum 500 characters")
    private String description;

    @NotNull
    private Task.Status status;

    @Positive
    @Max(value = 200, message = "Estimated hours can not be more than 200")
    private Integer estimatedHours;
}
