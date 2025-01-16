package uz.pdp.anicinema.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ShortsResponse {

    private Long id;

    private String title;

    private String url;

    private List<CommentResponse> comments;

}
