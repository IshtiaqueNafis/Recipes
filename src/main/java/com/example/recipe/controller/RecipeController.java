package com.example.recipe.controller;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
public class RecipeController {
    private RecipeService recipeService;


    @GetMapping("/recipes")
    public String recipes(Model model) {
        List<RecipeDto> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipes";
    }

    @GetMapping(value = {"/recipe_form" ,"recipe_form/{id}"})
    public String newRecipeForm(Model model,  @PathVariable( value = "id", required = false) Long id) {

        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "recipe_form";
    }

    //handler method to handle submit request
    @PostMapping("/submit_recipe")
    public String createRecipe(@Valid @ModelAttribute("recipe") RecipeDto recipeDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("recipe", recipeDto);
            return "recipe_form";
        }
        recipeService.submitRecipe(recipeDto);
        return "redirect:/recipes";


    }



}
