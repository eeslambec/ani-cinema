package uz.pdp.anicinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.anicinema.entity.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository  extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);

}
