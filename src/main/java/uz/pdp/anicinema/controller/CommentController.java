package uz.pdp.anicinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.anicinema.payload.request.CommentRequest;
import uz.pdp.anicinema.payload.response.MessageResponse;
import uz.pdp.anicinema.service.CommentService;

import static uz.pdp.anicinema.payload.response.ResponseData.ok;
import static uz.pdp.anicinema.utils.contant.AppConstant.API_V1;
import static uz.pdp.anicinema.utils.contant.AppConstant.COMMENT;
import static uz.pdp.anicinema.utils.contant.MessageKey.SUCCESS_MESSAGE;
import static uz.pdp.anicinema.utils.enums.Code.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + COMMENT)
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest request) {
        return ok(SUCCESS,commentService.addComment(request));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> delete(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ok(SUCCESS, new MessageResponse(SUCCESS_MESSAGE));
    }

    @PutMapping("/reply/{commentId}")
    public ResponseEntity<?> addReply(@RequestBody CommentRequest request, @PathVariable Long commentId) {
        return ok(SUCCESS, commentService.addReply(commentId, request));
    }

    @GetMapping("/list-by-movie/{movieId}")
    public ResponseEntity<?> getByMovie(@PathVariable Long movieId) {
        return ok(SUCCESS, commentService.getAllByMovieId(movieId));
    }

    @GetMapping("/list-by-shorts/{shortsId}")
    public ResponseEntity<?> getByShorts(@PathVariable Long shortsId) {
        return ok(SUCCESS, commentService.getAllByShortsId(shortsId));
    }

}
