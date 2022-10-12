package com.example.recipe.services.mapper;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.models.Recipe;

public class RecipeMapper {

    public RecipeDto mapToRecipeDto(Recipe recipe) {
        return RecipeDto.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .url(recipe.getUrl())
                .description(recipe.getDescription())
                .createdOn(recipe.getCreatedOn())
                .updatedOn(recipe.getUpdatedOn()).build();
    }

    public Recipe mapToRecipe(RecipeDto recipeDto) {
        return Recipe.builder()
                .id(recipeDto.getId())
                .name(recipeDto.getName())
                .url(recipeDto.getUrl())
                .description(recipeDto.getDescription())
                .createdOn(recipeDto.getCreatedOn())
                .updatedOn(recipeDto.getUpdatedOn()).build();
    }
}
