package freelancehub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name="CLIENT_NAME", length=50, nullable=false, unique=false)
    private String name;
    @Column(name="CLIENT_EMAIL", length=50, nullable=false, unique=false)
    private String email;
    @Column(name="CLIENT_PHONE", length=50, nullable=true, unique=false)
    private String phone;
    @Column(name="CLIENT_BILLING_ADDRESS", length=20, nullable=false, unique=false)
    private String billingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Project> projects;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Invoice> invoices;

}
