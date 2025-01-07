package uz.pdp.anicinema.entity;


import java.time.LocalDate;
import java.util.List;

public class Movie {
    private Long id;
    private String title;
    private String description;
    private Attachment thumbnail;
    private Genre genre;
    private Integer releaseYear;
    private String director;
    private String releasedCountry;
    private Attachment trailer;
    private Attachment banner;
    private List<Attachment> screenshots;
    private List<Attachment> episodes;
}
