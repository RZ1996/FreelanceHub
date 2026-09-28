package freelancehub.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name="USER_NAME", length=50, nullable=false, unique=false)
    private String name;
    @Column(name="USER_SURNAME", length=50, nullable=false, unique=false)
    private String surName;
    @Column(name="USER_EMAIL", length=100, nullable=false, unique=true)
    private String email;
    @Column(name="USER_PASSWORD", length=60, nullable=false, unique=false)
    private String password;
    @Column(name="REGISTERED_TIME", length=50, nullable=false, unique=false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Client> clients;


}
