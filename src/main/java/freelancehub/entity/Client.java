package freelancehub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;

@Entity
public class Client {

    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String name;
    private String email;
    private String billingAddress;
    private Date createdAt;
}
