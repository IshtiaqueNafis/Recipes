package ca.gbc_assignment.services;

import ca.gbc_assignment.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void createComment(Long recipeId, CommentDto commentDto);

    List<CommentDto> findAllComments();

    void deleteComment(Long commentId);

    List<CommentDto> findCommentByRecipes();
}
