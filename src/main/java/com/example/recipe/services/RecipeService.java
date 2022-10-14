package com.example.recipe.services;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.models.Recipe;

import java.util.List;

public interface RecipeService {
    List<RecipeDto> getAllRecipes();

    void createRecipe(RecipeDto recipeDto);

    void updateRecipe(RecipeDto recipeDto);

    RecipeDto findRecipeById(Long recipeId);

    void submitRecipe(RecipeDto recipe);

    List<RecipeDto> searchRecipes(String query);
    List<RecipeDto> findRecipeByUser();

}
