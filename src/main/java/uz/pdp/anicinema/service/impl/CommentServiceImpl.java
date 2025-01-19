package uz.pdp.anicinema.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.Comment;
import uz.pdp.anicinema.entity.Movie;
import uz.pdp.anicinema.entity.Shorts;
import uz.pdp.anicinema.entity.User;
import uz.pdp.anicinema.exception.BadRequestException;
import uz.pdp.anicinema.mapper.CommentMapper;
import uz.pdp.anicinema.payload.request.CommentRequest;
import uz.pdp.anicinema.payload.response.CommentResponse;
import uz.pdp.anicinema.repository.CommentRepository;
import uz.pdp.anicinema.repository.MovieRepository;
import uz.pdp.anicinema.repository.ShortsRepository;
import uz.pdp.anicinema.security.CustomUserDetails;
import uz.pdp.anicinema.service.CommentService;
import uz.pdp.anicinema.service.UserService;
import uz.pdp.anicinema.utils.SecurityUtils;
import uz.pdp.anicinema.utils.enums.Status;
import uz.pdp.anicinema.utils.enums.VideoType;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserService userService;
    private final CommentMapper commentMapper;
    private final MovieRepository movieRepository;
    private final ShortsRepository shortsRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponse addComment(CommentRequest request) {

        User user = userService.findById(request.getUserId());

        Comment comment = commentMapper.toEntity(request, user);

        comment = commentRepository.save(comment);

        if (request.getVideoType().equals(VideoType.MOVIE)) {

            Movie movie = movieRepository.findById(request.getVideoId())
                    .orElseThrow(BadRequestException::movieNotFound);

            movie.getComments().add(comment);

            movieRepository.save(movie);

        }else {

            Shorts shorts = shortsRepository.findById(request.getVideoId())
                    .orElseThrow(BadRequestException::shortsNotFound);

            shorts.getComments().add(comment);

            shortsRepository.save(shorts);

        }

        return commentMapper.toResponse(comment);

    }

    @Override
    public void deleteComment(Long commentId) {

        CustomUserDetails currentUser = SecurityUtils.getCurrentUser();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(BadRequestException::commentNotFound);

        if (currentUser != null && currentUser.getUserId().equals(comment.getUser().getId())) {

            if (comment.getReplies() != null && !comment.getReplies().isEmpty()) {
                commentRepository.deleteAll(comment.getReplies());
            }

            commentRepository.delete(comment);

            return;

        }

        throw BadRequestException.permissionDenied();

    }

    @Override
    @Transactional
    public CommentResponse addReply(Long parentCommentId, CommentRequest request) {

        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(BadRequestException::commentNotFound);

        User user = userService.findById(request.getUserId());

        Comment reply = commentMapper.toEntity(request, user);
        reply.setParent(parentComment);

        commentRepository.save(reply);

        return commentMapper.toResponse(parentComment);
    }

    @Override
    public List<CommentResponse> getAllByMovieId(Long movieId) {
        return commentRepository.findAllByMovieId(movieId)
                .stream()
                .map(commentMapper::toResponse)
                .toList();
    }

    @Override
    public List<CommentResponse> getAllByShortsId(Long shortsId) {
        return commentRepository.findAllByShortsId(shortsId)
                .stream()
                .map(commentMapper::toResponse)
                .toList();
    }

    @Override
    public List<CommentResponse> getAllActive() {

        List<Comment> all = commentRepository.findAll();

        List<CommentResponse> allActive = new ArrayList<>();

        for (Comment comment : all) {

            if (comment.getStatus() == Status.ACTIVE)

                allActive.add(commentMapper.toResponse(comment));

        }

        return allActive;

    }

}
