package freelancehub.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientDTO {
    private Long id;

    @NotBlank
    @Size(max = 50, message = "For name is maximum 50 characters")
    private String name;

    @NotBlank
    @Size(max = 50, message = "For email is maximum 50 characters")
    private String email;

    @Size(max = 50, message = "For phone is maximum 50 characters")
    private String phone;

    @NotBlank
    @Size(max = 50, message = "For billing address is maximum 50 characters")
    private String billingAddress;
}
