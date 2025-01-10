package uz.pdp.anicinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.anicinema.entity.Episode;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
