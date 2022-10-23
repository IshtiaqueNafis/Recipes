package ca.gbc_assignment.services.impl;

import ca.gbc_assignment.dto.CommentDto;
import ca.gbc_assignment.models.Comment;
import ca.gbc_assignment.mapper.CommentMapper;
import ca.gbc_assignment.models.Recipe;
import ca.gbc_assignment.models.User;
import ca.gbc_assignment.repository.CommentRepository;
import ca.gbc_assignment.repository.RecipeRepository;
import ca.gbc_assignment.repository.UserRepository;
import ca.gbc_assignment.services.CommentService;
import ca.gbc_assignment.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "Implements all crud operation for CommentService "
 */
//endregion
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private RecipeRepository recipeRepository;
    private UserRepository userRepository;


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

    @Override
    public List<CommentDto> findCommentByRecipes() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Long userId = user.getId();
        List<Comment> comments = commentRepository.findCommentByRecipe(userId);
        return comments.stream().map((comment) -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());
    }

}
