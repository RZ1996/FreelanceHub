package freelancehub.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String surName;
    private String email;
    private String password;
    private Date createdAt;

    public User(String name, String surName, String email, String password, Date createdAt){
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;

    }
}
