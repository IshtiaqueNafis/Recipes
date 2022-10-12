package com.example.recipe.controller;

import com.example.recipe.dto.CommentDto;
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


    @GetMapping(value = {"/recipe_form", "recipe_form/{id}"})
    public String newRecipeForm(Model model, @PathVariable(value = "id", required = false) Long id) {

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

    @GetMapping(value = "recipe_details/{id}")
    public String viewRecipeDetails(@PathVariable("id") long id, Model model) {
        RecipeDto recipeDto = recipeService.findRecipeById(id);
        model.addAttribute("recipe", recipeDto);
        CommentDto commentdto = new CommentDto();
        model.addAttribute("comment",commentdto);
        return "view_recipe";

    }


    @RequestMapping(path = {"/recipes"})
    public String searchPosts(Model model) {
        List<RecipeDto> recipes;
        recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);

        return "recipes";
    }


}
