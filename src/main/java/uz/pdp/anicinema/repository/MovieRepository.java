package uz.pdp.anicinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.anicinema.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
}
