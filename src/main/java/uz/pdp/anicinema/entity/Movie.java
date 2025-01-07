package uz.pdp.anicinema.entity;


import jakarta.persistence.*;
import lombok.*;

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
    private String genre;
    private Integer releaseYear;
    private String director;
    private String releasedCountry;
    @ManyToOne
    private Attachment trailer;
    @ManyToOne
    private Attachment banner;
    @OneToMany
    private List<Attachment> screenshots;
    @OneToMany
    private List<Attachment> episodes;
}
