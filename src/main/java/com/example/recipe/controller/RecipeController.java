package com.example.recipe.controller;

import com.example.recipe.dto.CommentDto;
import com.example.recipe.dto.RecipeDto;
import com.example.recipe.services.CommentService;
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
    private CommentService commentService;


    @GetMapping(value = {"/registered/recipe_form", "registered/recipe_form/{id}"})
    public String newRecipeForm(Model model, @PathVariable(value = "id", required = false) Long id) {

        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "registered/recipe_form";
    }

    //handler method to handle submit request
    @PostMapping("/registered/submit_recipe")
    public String createRecipe(@Valid @ModelAttribute("recipe") RecipeDto recipeDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("recipe", recipeDto);
            return "registered/recipe_form";
        }
        recipeService.submitRecipe(recipeDto);
        return "redirect:/registered/recipes";


    }




    //show list of comment request.

    @GetMapping("/registered/comments")
    public String recipeComments(Model model) {
        List<CommentDto> comments = commentService.findAllComments();
        model.addAttribute("comments", comments);
        return "registered/comments";

    }

    @GetMapping(path = {"/registered/recipes"})
    public String searchPosts(Model model) {
        List<RecipeDto> recipes;
        recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);

        return "registered/recipes";
    }

    @GetMapping("/registered/recipes/comment/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "registered/comments";
    }


}
