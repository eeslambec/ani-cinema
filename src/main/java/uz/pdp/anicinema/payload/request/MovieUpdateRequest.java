package uz.pdp.anicinema.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.anicinema.utils.enums.MovieStatus;

import java.util.List;

import static uz.pdp.anicinema.utils.contant.MessageKey.ID_NOT_VALID;

@Getter
@AllArgsConstructor
public class MovieUpdateRequest {

    @NotNull(message = ID_NOT_VALID)
    private Long id;

    private String title;

    private Integer releaseYear;

    private Double rating;

    private String genre;

    private String description;

    private String director;

    private String country;

    private Boolean isReleased;

    private MovieStatus status;

    private Long nextSeasonId;

    private String type;

    private Long trailerId;

    private Long bannerId;

    private Long thumbnailId;

    private List<Long> screenshotIds;

    private List<Long> episodeIds;

}
