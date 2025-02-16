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

    @Mapping(target = "thumbnail", expression = "java(movie.getThumbnail().getUrl())")
    @Mapping(target = "banner", expression = "java(movie.getBanner().getUrl())")
    @Mapping(target = "screenshots", expression = "java(movie.getScreenshots().stream().map(uz.pdp.anicinema.entity.Attachment::getUrl).toList())")
    @Mapping(target = "genre", expression = "java(movie.getGenre().getName())")
    @Mapping(target = "type", expression = "java(movie.getType().getName())")
    MovieResponse toResponse(Movie movie);

    @Mapping(target = "previousSeason", ignore = true)
    @Mapping(target = "nextSeason", ignore = true)
    @Mapping(target = "trailer", ignore = true)
    @Mapping(target = "releasedCountry", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "movieStatus", expression = "java(uz.pdp.anicinema.utils.enums.MovieStatus.WAITING)")
    @Mapping(target = "comments", expression = "java(new java.util.ArrayList<>())")
    @Mapping(target = "thumbnail", expression = "java(attachmentService.findById(request.getThumbnailId()))")
    @Mapping(target = "banner", expression = "java(attachmentService.findById(request.getBannerId()))")
    @Mapping(target = "status", expression = "java(uz.pdp.anicinema.utils.enums.Status.ACTIVE)")
    @Mapping(target = "screenshots", expression = "java(request.getScreenshotIds().stream().map(attachmentService::findById).toList())")
    @Mapping(target = "episodes", expression = "java(request.getEpisodes().stream().map(episodeService::save).toList())")
    Movie toEntity(MovieCreateRequest request, @Context AttachmentService attachmentService, @Context EpisodeService episodeService);

    @Mapping(target = "previousSeason", ignore = true)
    @Mapping(target = "nextSeason", ignore = true)
    @Mapping(target = "trailer", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "releasedCountry", source = "country")
    @Mapping(target = "thumbnail", expression = "java(attachmentService.findById(request.getThumbnailId()))")
    @Mapping(target = "banner", expression = "java(attachmentService.findById(request.getBannerId()))")
    @Mapping(target = "status", expression = "java(uz.pdp.anicinema.utils.enums.Status.ACTIVE)")
    @Mapping(target = "movieStatus", expression = "java(uz.pdp.anicinema.utils.enums.MovieStatus.WAITING)")
    @Mapping(target = "screenshots", expression = "java(request.getScreenshotIds().stream().map(attachmentService::findById).toList())")
    @Mapping(target = "episodes", expression = "java(request.getEpisodeIds().stream().map(episodeService::getById).toList())")
    Movie toEntity(MovieUpdateRequest request, @Context AttachmentService attachmentService, @Context EpisodeService episodeService);

}
