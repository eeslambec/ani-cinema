package uz.pdp.anicinema.service;

import org.springframework.stereotype.Service;
import uz.pdp.anicinema.payload.request.MovieCreateRequest;
import uz.pdp.anicinema.payload.request.MovieUpdateRequest;
import uz.pdp.anicinema.payload.response.MovieResponse;
import uz.pdp.anicinema.utils.enums.MovieStatus;

import java.util.List;

@Service
public interface MovieService {

    void save(MovieCreateRequest request);

    void update(MovieUpdateRequest request);

    void delete(Long id);

    MovieResponse getById(Long id);

    List<MovieResponse> getAll();

    List<MovieResponse> getAllSoon();

    List<MovieResponse> getAllByStatus(MovieStatus status);

    List<MovieResponse> getAllTopRated();

}
