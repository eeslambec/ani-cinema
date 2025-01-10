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

    private Long thumbnailId;

    private String director;

    private Integer releaseYear;

    private Double rating;

    private String genre;

    private String releasedCountry;

    private Boolean isActive;

    private Trailer trailer;

    private Long bannerId;

    private List<Long> screenshotIds;

    private List<Long> episodeIds;

    private List<Long> commentIds;

}
