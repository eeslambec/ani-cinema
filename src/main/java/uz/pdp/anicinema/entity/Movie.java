package uz.pdp.anicinema.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
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

    private String genre;

    private String releasedCountry;

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
}
