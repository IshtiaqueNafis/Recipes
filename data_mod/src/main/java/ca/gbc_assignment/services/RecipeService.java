package ca.gbc_assignment.services;

import ca.gbc_assignment.dto.RecipeDto;
import ca.gbc_assignment.exception.NotFoundException;
import ca.gbc_assignment.models.User;

import java.util.List;

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
