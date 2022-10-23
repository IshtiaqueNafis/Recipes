package ca.gbc_assignment.controller;


import ca.gbc_assignment.dto.CommentDto;
import ca.gbc_assignment.dto.RecipeDto;
import ca.gbc_assignment.exception.NotFoundException;
import ca.gbc_assignment.repository.UserRepository;
import ca.gbc_assignment.services.CommentService;
import ca.gbc_assignment.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.List;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): < Nafis Ishtiaque,Naveen Jose mahmoud farghali>
 * Student Number: < 101206872,101238395,101347618 >
 * Date: October 23, 2022
 * Description: "Home controller is responsible for showing routes for unregistred and registered user also meal plan was put on here cause it was giving errors"
 */
//endregion
@Controller
@AllArgsConstructor
public class HomeController {

    private RecipeService recipeService;
    private CommentService commentService;
    private UserRepository userRepository;

    @GetMapping(path = {"/", "/search", "/filter/{filter}"})
    public String home(Model model, String query, @PathVariable(required = false, name = "filter") String filter) {
        List<RecipeDto> recipes;
        String title = "";
        if (query == null && filter == null) {
            title = "Showing all Recipes";
            recipes = recipeService.findRecipeForHomePage();
        } else if (query == null && filter != null) {
            title = MessageFormat.format("Showing Recipes by {0} Criteria", filter);
            recipes = recipeService.findRecipeBasedOnFilter(filter);

        } else {
            title = MessageFormat.format("Showing recipes by {0} Criteria", query);
            recipes = recipeService.searchRecipes(query);
        }

        model.addAttribute("title", title);
        model.addAttribute("recipes", recipes);
        model.addAttribute("query", query);
        return "home";
    }

    @GetMapping(value = "recipe_details/{id}")
    public String viewRecipeDetails(@PathVariable("id") long id, Model model) {
        RecipeDto recipeDto = null;
        try {
            recipeDto = recipeService.findRecipeById(id);
            model.addAttribute("recipe", recipeDto);
            CommentDto commentdto = new CommentDto();
            model.addAttribute("comment", commentdto);
            var user = recipeService.findUserFromRecipeId(id);
            model.addAttribute("user", user.getName());
            return "view_recipe";
        } catch (NotFoundException e) {
            return "error";
        }


    }

    @PostMapping("/{recipeId}/comments")
    public String createComments(@PathVariable("recipeId") long recipeId, @Valid @ModelAttribute("comment") CommentDto commentDto, BindingResult result, Model model) {


        RecipeDto recipeDto = null;
        try {
            recipeDto = recipeService.findRecipeById(recipeId);
            if (result.hasErrors()) {
                model.addAttribute("recipe", recipeDto);
                model.addAttribute("comment", commentDto);
                return "view_recipe";
            }

            commentService.createComment(recipeId, commentDto);
            return "redirect:/recipe_details/" + recipeId;
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @GetMapping("/meal_plan")
    public String ViewMealPlan(Model model) {

        model.addAttribute("recipes", recipeService.getAllRecipes());

        return "meal_plan";
    }



}
