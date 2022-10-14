package com.example.recipe.services;

import com.example.recipe.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void createComment(Long recipeId, CommentDto commentDto);

    List<CommentDto> findAllComments();

    void deleteComment(Long commentId);

    List<CommentDto> findCommentByRecipes();
}
