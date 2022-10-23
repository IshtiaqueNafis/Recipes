package com.example.recipe.services;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.exception.NotFoundException;
import com.example.recipe.models.Recipe;
import com.example.recipe.models.User;

import java.util.List;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "Forms contanct for RecipeServiceImpl"
 */
//endregion
public interface RecipeService {
    List<RecipeDto> getAllRecipes();

    void createRecipe(RecipeDto recipeDto);

    void updateRecipe(RecipeDto recipeDto);

    RecipeDto findRecipeById(Long recipeId) throws NotFoundException;

    void submitRecipe(RecipeDto recipe);

    List<RecipeDto> searchRecipes(String query);
    List<RecipeDto> findRecipeByUser();

    List<RecipeDto> findRecipeForHomePage();

    List<RecipeDto> findRecipeBasedOnFilter(String filter);

    void updateRecipeForOtherUser(Long id);

    User findUserFromRecipeId(Long id);


}
