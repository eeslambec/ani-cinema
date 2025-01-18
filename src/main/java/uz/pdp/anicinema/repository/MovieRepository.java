package uz.pdp.anicinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.anicinema.entity.Movie;
import uz.pdp.anicinema.utils.enums.MovieStatus;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findAllByIsReleased(Boolean isReleased);

    List<Movie> findAllByMovieStatus(MovieStatus status);

    @Query("SELECT m FROM Movie m ORDER BY m.rating LIMIT 100")
    List<Movie> findAllTopRated();

}
