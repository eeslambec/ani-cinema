package uz.pdp.anicinema.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.pdp.anicinema.entity.Movie;
import uz.pdp.anicinema.payload.request.MovieCreateRequest;
import uz.pdp.anicinema.payload.request.MovieUpdateRequest;
import uz.pdp.anicinema.payload.response.MovieResponse;
import uz.pdp.anicinema.service.AttachmentService;
import uz.pdp.anicinema.service.EpisodeService;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "thumbnailId", expression = "java(movie.getThumbnail().getId())")
    @Mapping(target = "bannerId", expression = "java(movie.getBanner().getId())")
    @Mapping(target = "screenshotIds", expression = "java(movie.getScreenshots().stream().map(uz.pdp.anicinema.entity.Attachment::getId).toList())")
    @Mapping(target = "commentIds", expression = "java(movie.getComments().stream().map(uz.pdp.anicinema.entity.Comment::getId).toList())")
    @Mapping(target = "episodeIds", expression = "java(movie.getEpisodes().stream().map(uz.pdp.anicinema.entity.Episode::getId).toList())")
    MovieResponse toResponse(Movie movie);

    @Mapping(target = "trailer", ignore = true)
    @Mapping(target = "releasedCountry", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "thumbnail", expression = "java(attachmentService.findById(request.getThumbnailId()))")
    @Mapping(target = "banner", expression = "java(attachmentService.findById(request.getBannerId()))")
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "screenshots", expression = "java(request.getScreenshotIds().stream().map(attachmentService::findById).toList())")
    @Mapping(target = "episodes", expression = "java(request.getEpisodes().stream().map(episodeService::save).toList())")
    Movie toEntity(MovieCreateRequest request, @Context AttachmentService attachmentService, @Context EpisodeService episodeService);

    @Mapping(target = "trailer", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "releasedCountry", source = "country")
    @Mapping(target = "thumbnail", expression = "java(attachmentService.findById(request.getThumbnailId()))")
    @Mapping(target = "banner", expression = "java(attachmentService.findById(request.getBannerId()))")
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "screenshots", expression = "java(request.getScreenshotIds().stream().map(attachmentService::findById).toList())")
    @Mapping(target = "episodes", expression = "java(request.getEpisodeIds().stream().map(episodeService::getById).toList())")
    Movie toEntity(MovieUpdateRequest request, @Context AttachmentService attachmentService, @Context EpisodeService episodeService);

}
