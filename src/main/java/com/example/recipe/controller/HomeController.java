package com.example.recipe.controller;

import com.example.recipe.dto.CommentDto;
import com.example.recipe.dto.RecipeDto;
import com.example.recipe.models.Comment;
import com.example.recipe.services.CommentService;
import com.example.recipe.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private RecipeService recipeService;
    private CommentService commentService;

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


    @PostMapping("/{recipeId}/comments")
    public String createComments(@PathVariable("recipeId") long recipeId, @Valid @ModelAttribute("comment") CommentDto commentDto, BindingResult result,Model model) {

        RecipeDto recipeDto = recipeService.findRecipeById(recipeId);
        if(result.hasErrors()){
            model.addAttribute("recipe",recipeDto);
            model.addAttribute("comment", commentDto);
            return "view_recipe";
        }

        commentService.createComment(recipeId, commentDto);
        return "redirect:/recipe_details/" + recipeId;

    }

}
