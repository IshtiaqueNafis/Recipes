package com.example.recipe.services.impl;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.mapper.RecipeMapper;
import com.example.recipe.models.Favourites;
import com.example.recipe.models.MealPlanner;
import com.example.recipe.models.Recipe;
import com.example.recipe.models.User;
import com.example.recipe.repository.FavouritesRepository;
import com.example.recipe.repository.MealPlannerRepository;
import com.example.recipe.repository.RecipeRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.services.RecipeService;
import com.example.recipe.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;
    private UserRepository userRepository;

    private FavouritesRepository favouritesRepository;
    private MealPlannerRepository mealPlannerRepository;

    @Override
    public List<RecipeDto> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(recipe -> new RecipeMapper().mapToRecipeDto(recipe)).collect(Collectors.toList());

    }

    @Override
    public void createRecipe(RecipeDto recipeDto) {
        Recipe recipe = new RecipeMapper().mapToRecipe(recipeDto);
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        recipe.setCreatedBy(user);
        recipeRepository.save(recipe);
    }

    @Override
    public void updateRecipe(RecipeDto recipeDto) {

        Recipe recipe = new RecipeMapper().mapToRecipe(recipeDto);
        recipe.setCreatedOn(recipeDto.getCreatedOn());
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        recipe.setCreatedBy(user);
        recipeRepository.save(recipe);

    }

    @Override
    public RecipeDto findRecipeById(Long recipeId) {

        if (recipeId == null) {
            return new RecipeDto();
        } else {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            return new RecipeMapper().mapToRecipeDto(recipe);


        }


    }

    @Override
    public void submitRecipe(RecipeDto recipeDto) {
        if (recipeDto.getId() == null) {

            createRecipe(recipeDto);
        } else {
            recipeDto.setCreatedOn(recipeDto.getCreatedOn());
            updateRecipe(recipeDto);
        }
    }

    @Override
    public List<RecipeDto> searchRecipes(String query) {
        List<Recipe> recipes = recipeRepository.searchRecipes(query);
        return recipes.stream().map(recipe -> new RecipeMapper().mapToRecipeDto(recipe)).collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> findRecipeByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Long UserId = user.getId();
        List<Recipe> recipes = recipeRepository.findRecipesByUser(UserId);
        return recipes.stream().map((recipe -> new RecipeMapper().mapToRecipeDto(recipe))).collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> findRecipeForHomePage() {
        List<Recipe> recipes = recipeRepository.findRecipesPublic();
        return recipes.stream().map(recipe -> new RecipeMapper().mapToRecipeDto(recipe)).collect(Collectors.toList());
    }

    @Override
    public void updateRecipeForOtherUser(Long id) {
        Recipe recipe = recipeRepository.findById(id).get();
        List<Favourites> favourites = favouritesRepository.findAll();
        List<MealPlanner> mealPlans = (List<MealPlanner>) mealPlannerRepository.findAll();


        for (Favourites favourite : favourites) {
            if (favourite.getUser().getId() != recipe.getCreatedBy().getId() && favourite.getRecipe().getId() == recipe.getId()) {
                favouritesRepository.deleteById(favourite.getId());
            }
        }

        for (MealPlanner mealPlan : mealPlans) {
            if (mealPlan.getUser().getId() != recipe.getCreatedBy().getId() && mealPlan.getRecipe().getId() == recipe.getId()) {
                mealPlannerRepository.deleteById(mealPlan.getId());
            }
        }


    }

    private static String getUrl(String recipeName) {
        String title = recipeName.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[A-Za-z0-9]", "-");
        return url;
    }


}
