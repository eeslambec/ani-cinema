package uz.pdp.anicinema.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.anicinema.utils.enums.MovieStatus;
import uz.pdp.anicinema.utils.enums.Status;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("status = 'ACTIVE'")
@SQLDelete(sql = "UPDATE movie m SET status = 'DELETED' WHERE id = ?")
public class Movie extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    private Attachment thumbnail;

    private String director;

    private Integer releaseYear;

    private Double rating;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Genre genre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private MovieType type;

    private String releasedCountry;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Boolean isReleased;

    @Enumerated(EnumType.STRING)
    private MovieStatus movieStatus;

    @ManyToOne
    private Trailer trailer;

    @ManyToOne
    private Attachment banner;

    @OneToMany
    @JoinTable(
            name = "movie_screenshots",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "screenshot_id", foreignKey = @ForeignKey(name = "screenshot_id"))

    )
    private List<Attachment> screenshots;

    @OneToMany
    @JoinTable(
            name = "movie_episodes",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id", foreignKey = @ForeignKey(name = "episode_id"))

    )
    private List<Episode> episodes;

    @OneToMany
    @JoinTable(
            name = "movie_comments",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id", foreignKey = @ForeignKey(name = "comment_id"))

    )
    private List<Comment> comments;

    @OneToOne
    private Movie previousSeason;

    @OneToOne
    private Movie nextSeason;

}
