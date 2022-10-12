package com.example.recipe.services.impl;

import com.example.recipe.dto.CommentDto;
import com.example.recipe.mapper.CommentMapper;
import com.example.recipe.models.Comment;
import com.example.recipe.models.Recipe;
import com.example.recipe.repository.CommentRepository;
import com.example.recipe.repository.RecipeRepository;
import com.example.recipe.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private RecipeRepository recipeRepository;


    @Override
    public void createComment(Long recipeId, CommentDto commentDto) {

        Recipe recipe = recipeRepository.findById(recipeId).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setRecipe(recipe);
        commentRepository.save(comment);


    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(comment -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
