package uz.pdp.anicinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.anicinema.entity.Genre;
import uz.pdp.anicinema.entity.Movie;
import uz.pdp.anicinema.entity.MovieType;
import uz.pdp.anicinema.exception.BadRequestException;
import uz.pdp.anicinema.mapper.MovieMapper;
import uz.pdp.anicinema.payload.request.MovieCreateRequest;
import uz.pdp.anicinema.payload.request.MovieUpdateRequest;
import uz.pdp.anicinema.payload.response.MovieResponse;
import uz.pdp.anicinema.repository.GenreRepository;
import uz.pdp.anicinema.repository.MovieRepository;
import uz.pdp.anicinema.repository.MovieTypeRepository;
import uz.pdp.anicinema.service.AttachmentService;
import uz.pdp.anicinema.service.EpisodeService;
import uz.pdp.anicinema.service.MovieService;
import uz.pdp.anicinema.utils.enums.MovieStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;
    private final EpisodeService episodeService;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final AttachmentService attachmentService;
    private final MovieTypeRepository movieTypeRepository;

    @Override
    public void save(MovieCreateRequest request) {

        Movie movie = movieMapper.toEntity(request, attachmentService, episodeService);

        Genre genre = genreRepository.findByName(request.getGenre())
                .orElse(Genre.builder().name(request.getGenre()).build());

        MovieType movieType = movieTypeRepository.findByName(request.getType())
                .orElse(MovieType.builder().name(request.getType()).build());

        movie.setGenre(genre);
        movie.setType(movieType);

        movieRepository.save(movie);

    }

    @Override
    public void update(MovieUpdateRequest request) {

        Movie movie = movieMapper.toEntity(request, attachmentService, episodeService);

        Movie nextSeason = movieRepository.findById(request.getNextSeasonId())
                .orElseThrow(BadRequestException::movieNotFound);

        Genre genre = genreRepository.findByName(request.getGenre())
                .orElse(Genre.builder().name(request.getGenre()).build());

        MovieType movieType = movieTypeRepository.findByName(request.getType())
                .orElse(MovieType.builder().name(request.getType()).build());

        movie.setNextSeason(nextSeason);
        movie.setGenre(genre);
        movie.setType(movieType);

        nextSeason.setPreviousSeason(movie);

        movieRepository.saveAll(List.of(nextSeason, movie));

    }

    @Override
    public void delete(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(BadRequestException::movieNotFound);

        movieRepository.delete(movie);

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

    @Override
    public List<MovieResponse> getAllSoon() {

        return movieRepository.findAllByIsReleased(false)
                .stream()
                .map(movieMapper::toResponse)
                .toList();

    }

    @Override
    public List<MovieResponse> getAllByStatus(MovieStatus status) {

        return movieRepository.findAllByMovieStatus(status)
                .stream()
                .map(movieMapper::toResponse)
                .toList();

    }

    @Override
    public List<MovieResponse> getAllTopRated() {

        return movieRepository.findAllTopRated()
                .stream()
                .map(movieMapper::toResponse)
                .toList();

    }
}
