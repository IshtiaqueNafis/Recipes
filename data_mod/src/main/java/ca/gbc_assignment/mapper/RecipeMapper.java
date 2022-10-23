package ca.gbc_assignment.mapper;

import ca.gbc_assignment.dto.RecipeDto;
import ca.gbc_assignment.models.Recipe;

import java.util.stream.Collectors;

public class RecipeMapper {

    public RecipeDto mapToRecipeDto(Recipe recipe) {
        return RecipeDto.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .createdOn(recipe.getCreatedOn())
                .comments(recipe.getComments().stream().map((comment) -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toSet()))
                .updatedOn(recipe.getUpdatedOn())
                .type(recipe.getType())
                .photo(recipe.getPhoto())
                .difficultyLevel(recipe.getDifficultyLevel())
                .availability(recipe.isAvailability())
                .calories(recipe.getCalories())

                .build();
    }

    public Recipe mapToRecipe(RecipeDto recipeDto) {
        return Recipe.builder()
                .id(recipeDto.getId())
                .name(recipeDto.getName())
                .description(recipeDto.getDescription())
                .createdOn(recipeDto.getCreatedOn())
                .comments(recipeDto.getComments().stream().map((commentDto) -> CommentMapper.mapToComment(commentDto)).collect(Collectors.toSet()))
                .updatedOn(recipeDto.getUpdatedOn())
                .difficultyLevel(recipeDto.getDifficultyLevel())
                .availability(recipeDto.isAvailability())
                .photo(recipeDto.getPhoto())
                .calories(recipeDto.getCalories())

                .type(recipeDto.getType())
                .build();
    }
}
