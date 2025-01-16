package uz.pdp.anicinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.anicinema.entity.Shorts;

@Repository
public interface ShortsRepository extends JpaRepository<Shorts, Long> {
}
