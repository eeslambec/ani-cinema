package uz.pdp.anicinema.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.anicinema.utils.enums.VideoType;

@Getter
@AllArgsConstructor
public class CommentRequest {

    private Long videoId;

    private Long userId;

    private String comment;

    private VideoType videoType;

}
