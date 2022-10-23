package ca.gbc_assignment.repository;

import ca.gbc_assignment.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "select c.* from comments c inner join recipes r where c.recipe_id=r.id and r.created_by = :userId",nativeQuery = true)
    List<Comment> findCommentByRecipe(Long userId);

}
