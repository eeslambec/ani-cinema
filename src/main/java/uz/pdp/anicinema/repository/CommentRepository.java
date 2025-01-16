package uz.pdp.anicinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.anicinema.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Movie m JOIN m.comments c WHERE m.id = :movieId")
    List<Comment> findAllByMovieId(@RequestParam("movieId") Long movieId);

    @Query("SELECT c FROM Shorts s JOIN s.comments c WHERE s.id = :shortsId")
    List<Comment> findAllByShortsId(@RequestParam("shortsId") Long shortsId);

}
