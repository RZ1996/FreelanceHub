package freelancehub.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    private String name;
    private String surName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
