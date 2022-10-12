package com.example.recipe.controller;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private RecipeService recipeService;

    @GetMapping(path = {"/", "/search"})
    public String home(Model model, String query) {
        List<RecipeDto> recipes;
        if (query == null) {

            recipes = recipeService.getAllRecipes();
        } else {
            recipes = recipeService.searchRecipes(query);
        }
        model.addAttribute("recipes", recipes);
        model.addAttribute("query", query);
        return "home";
    }

}
