package uz.pdp.anicinema.service;

import org.springframework.stereotype.Service;
import uz.pdp.anicinema.payload.request.CommentRequest;
import uz.pdp.anicinema.payload.response.CommentResponse;

import java.util.List;

@Service
public interface CommentService {

    CommentResponse addComment(CommentRequest request);

    void deleteComment(Long commentId);

    CommentResponse addReply(Long parentCommentId, CommentRequest request);

    List<CommentResponse> getAllByMovieId(Long movieId);

    List<CommentResponse> getAllByShortsId(Long shortsId);

}
