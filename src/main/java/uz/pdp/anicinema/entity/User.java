package uz.pdp.anicinema.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.anicinema.utils.enums.Role;
import uz.pdp.anicinema.utils.enums.UserStatus;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    @ManyToOne
    private Attachment photo;
    private UserStatus status;
    @ManyToOne
    @Enumerated(EnumType.STRING)
    private Subscription subscription;
}
