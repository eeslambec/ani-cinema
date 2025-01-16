package uz.pdp.anicinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.anicinema.entity.Comment;
import uz.pdp.anicinema.entity.User;
import uz.pdp.anicinema.payload.request.CommentRequest;
import uz.pdp.anicinema.payload.response.CommentResponse;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    @Mapping(target = "userPhoto", expression = "java(comment.getUser().getPhoto().getUrl())")
    @Mapping(target = "comment", source = "text")
    @Mapping(target = "replies", expression = "java(comment.getReplies().stream().map(this::toResponse).toList())")
    CommentResponse toResponse(Comment comment);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "isActive" , constant = "true")
    @Mapping(target = "text", source = "request.comment")
    @Mapping(target = "replies", expression = "java(new java.util.ArrayList<>())")
    Comment toEntity(CommentRequest request, User user);

}

