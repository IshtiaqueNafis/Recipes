package com.example.recipe.services.mapper;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.mapper.CommentMapper;
import com.example.recipe.models.Recipe;

import java.util.stream.Collectors;

public class RecipeMapper {

    public RecipeDto mapToRecipeDto(Recipe recipe) {
        return RecipeDto.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .url(recipe.getUrl())
                .description(recipe.getDescription())
                .createdOn(recipe.getCreatedOn())
                .comments(recipe.getComments().stream().map((comment) -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toSet()))
                .updatedOn(recipe.getUpdatedOn()).build();
    }

    public Recipe mapToRecipe(RecipeDto recipeDto) {
        return Recipe.builder()
                .id(recipeDto.getId())
                .name(recipeDto.getName())
                .url(recipeDto.getUrl())
                .description(recipeDto.getDescription())
                .createdOn(recipeDto.getCreatedOn())
                .comments(recipeDto.getComments().stream().map((commentDto) -> CommentMapper.mapToComment(commentDto)).collect(Collectors.toSet()))
                .updatedOn(recipeDto.getUpdatedOn()).build();
    }
}
