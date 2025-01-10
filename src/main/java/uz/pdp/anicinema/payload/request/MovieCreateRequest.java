package uz.pdp.anicinema.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.anicinema.payload.EpisodeDto;

import java.util.List;

@AllArgsConstructor
@Getter
public class MovieCreateRequest {

    private String title;

    private Integer releaseYear;

    private Double rating;

    private String genre;

    private String description;

    private String director;

    private String country;

    private Long trailerId;

    private Long bannerId;

    private Long thumbnailId;

    private List<Long> screenshotIds;

    private List<EpisodeDto> episodes;

}
