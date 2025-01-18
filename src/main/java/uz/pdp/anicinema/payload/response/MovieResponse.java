package uz.pdp.anicinema.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.anicinema.entity.Trailer;

import java.util.List;

@AllArgsConstructor
@Getter
public class MovieResponse {

    private Long id;

    private String title;

    private String description;

    private String thumbnail;

    private String genre;

    private String type;

    private String director;

    private Integer releaseYear;

    private Double rating;

    private String releasedCountry;

    private Trailer trailer;

    private String banner;

    private List<String> screenshots;
}
