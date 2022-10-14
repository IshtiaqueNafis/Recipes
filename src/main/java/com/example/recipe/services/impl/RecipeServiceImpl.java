package com.example.recipe.services.impl;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.mapper.RecipeMapper;
import com.example.recipe.models.Recipe;
import com.example.recipe.models.User;
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
            recipeDto.setUrl(getUrl(recipeDto.getName()));
            createRecipe(recipeDto);
        } else {
            recipeDto.setUrl(getUrl(recipeDto.getName()));
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
      List <Recipe> recipes =  recipeRepository.findRecipesByUser(UserId);
          return  recipes.stream().map((recipe -> new RecipeMapper().mapToRecipeDto(recipe))).collect(Collectors.toList());
    }

    private static String getUrl(String recipeName) {
        String title = recipeName.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[A-Za-z0-9]", "-");
        return url;
    }


}
