package freelancehub.dto;
import lombok.Data;

@Data
public class ClientDTO {

    private Long Id;
    private String name;
    private String email;
    private String phone;
    private String billingAddress;
}
