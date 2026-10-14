package freelancehub.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class RegisterRequestDTO {

    @Email
    @NotBlank
    @Size(max = 50, message = "For email is maximum 50 characters")
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank
    @Size(max = 30, message = "For name is maximum 30 characters")
    private String name;

    @NotBlank
    @Size(max = 30, message = "For surname is maximum 30 characters")
    private String surName;
}
