package uz.pdp.anicinema.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.anicinema.utils.enums.Gender;
import uz.pdp.anicinema.utils.enums.Role;
import uz.pdp.anicinema.utils.enums.UserStatus;

import java.math.BigDecimal;
import java.util.List;

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

    private String username;

    private String email;

    private String bio;

    private String password;

    private Role role;

    @ManyToOne
    private Attachment photo;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private BigDecimal balance;

    private UserStatus status;

    private Long lvl;

    private Long lastLogin;

    @ManyToOne
    @Enumerated(EnumType.STRING)
    private Subscription subscription;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", foreignKey = @ForeignKey(name = "friend_id"))

    )
    private List<User> friends;

    @OneToMany
    @JoinTable(
            name = "user_liked_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", foreignKey = @ForeignKey(name = "movie_id"))

    )
    private List<Movie> likedMovies;

    @OneToMany
    @JoinTable(
            name = "user_watched_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", foreignKey = @ForeignKey(name = "movie_id"))
    )
    private List<Movie> watchedMovies;

    @OneToMany
    @JoinTable(
            name = "user_liked_shorts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "short_id", foreignKey = @ForeignKey(name = "short_id"))
    )
    private List<Shorts> likedShorts;
}
