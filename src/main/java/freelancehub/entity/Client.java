package freelancehub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Client {

    @jakarta.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String name;
    private String email;
    private String billingAddress;
    private Date createdAt;
}
