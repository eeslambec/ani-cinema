package uz.pdp.anicinema.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EpisodeDto {

    private Long number;

    private String title;

    private Long duration;

    private Long videoId;

}
