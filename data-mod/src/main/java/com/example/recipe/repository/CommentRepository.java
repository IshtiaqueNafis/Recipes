package com.example.recipe.repository;

import com.example.recipe.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {


}
