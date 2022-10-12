package com.example.recipe.services;

import com.example.recipe.dto.CommentDto;

public interface CommentService {
    void createComment(Long recipeId, CommentDto commentDto);
}
