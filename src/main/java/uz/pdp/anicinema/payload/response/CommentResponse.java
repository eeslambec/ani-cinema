package uz.pdp.anicinema.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CommentResponse {

    private String username;

    private String userPhoto;

    private String comment;

    private List<CommentResponse> replies;

}
