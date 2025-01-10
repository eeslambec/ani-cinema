package uz.pdp.anicinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.Movie;
import uz.pdp.anicinema.exception.BadRequestException;
import uz.pdp.anicinema.mapper.MovieMapper;
import uz.pdp.anicinema.payload.request.MovieCreateRequest;
import uz.pdp.anicinema.payload.request.MovieUpdateRequest;
import uz.pdp.anicinema.payload.response.MovieResponse;
import uz.pdp.anicinema.repository.MovieRepository;
import uz.pdp.anicinema.service.AttachmentService;
import uz.pdp.anicinema.service.EpisodeService;
import uz.pdp.anicinema.service.MovieService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final EpisodeService episodeService;
    private final MovieRepository movieRepository;
    private final AttachmentService attachmentService;
    private final MovieMapper movieMapper;

    @Override
    public void save(MovieCreateRequest request) {

        Movie movie = movieMapper.toEntity(request, attachmentService, episodeService);

        movieRepository.save(movie);

    }

    @Override
    public void update(MovieUpdateRequest request) {

        Movie movie = movieMapper.toEntity(request, attachmentService, episodeService);

        //todo comments

        movieRepository.save(movie);

    }

    @Override
    public void delete(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(BadRequestException::movieNotFound);

        movie.setIsActive(false);

        movieRepository.save(movie);
    }

    @Override
    public MovieResponse getById(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(BadRequestException::movieNotFound);

        return movieMapper.toResponse(movie);
    }

    @Override
    public List<MovieResponse> getAll() {

        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toResponse)
                .toList();
    }
}
