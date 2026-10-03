package freelancehub.dto;
import freelancehub.entity.Client;
import freelancehub.entity.Project;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal hourlyRate;
    private Project.Status status;
}
