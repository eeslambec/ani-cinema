package uz.pdp.anicinema.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.anicinema.entity.Shorts;
import uz.pdp.anicinema.payload.request.ShortsRequest;
import uz.pdp.anicinema.payload.response.ShortsResponse;
import uz.pdp.anicinema.service.AttachmentService;

@Mapper(componentModel = "spring")
public interface ShortsMapper {

    @Mapping(target = "url", expression = "java(shorts.getVideo().getUrl())")
    @Mapping(target = "comments", expression = "java(shorts.getComments().stream().map(commentMapper::toResponse).toList())")
    ShortsResponse toResponse(Shorts shorts, @Context CommentMapper commentMapper);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "video", expression = "java(attachmentService.findById(request.getVideoId()))")
    @Mapping(target = "comments", expression = "java(new java.util.ArrayList<>())")
    Shorts toEntity(ShortsRequest request, @Context AttachmentService attachmentService);

}
