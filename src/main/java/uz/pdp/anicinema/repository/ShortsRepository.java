package uz.pdp.anicinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.anicinema.entity.Shorts;

import java.util.List;

@Repository
public interface ShortsRepository extends JpaRepository<Shorts, Long> {

    @Query("SELECT sh FROM Shorts sh ORDER BY function('RANDOM') ")
    List<Shorts> findAllRandom();

}
