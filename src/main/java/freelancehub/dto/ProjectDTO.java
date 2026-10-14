package freelancehub.dto;
import freelancehub.entity.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectDTO {

    private Long id;

    @NotBlank
    @Size(max = 50, message = "For name is maximum 50 characters")
    private String name;

    @Size(max = 500, message = "For description is maximum 500 characters")
    private String description;

    @Positive
    @NotNull
    private BigDecimal hourlyRate;

    @NotNull
    private Project.Status status;
}
