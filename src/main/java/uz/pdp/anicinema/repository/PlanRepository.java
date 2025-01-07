package uz.pdp.anicinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.anicinema.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
