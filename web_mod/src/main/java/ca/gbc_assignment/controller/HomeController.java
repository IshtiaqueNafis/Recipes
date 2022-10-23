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
import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private RecipeService recipeService;
    private CommentService commentService;
    private UserRepository userRepository;

    @GetMapping(path = {"/", "/search", "/filter/{filter}"})
    public String home(Model model, String query, @PathVariable(required = false, name = "filter") String filter) {
        List<RecipeDto> recipes;
        if (query == null && filter == null) {
            recipes = recipeService.findRecipeForHomePage();
        } else if (query == null && filter != null) {
            recipes = recipeService.findRecipeBasedOnFilter(filter);

        } else {
            recipes = recipeService.searchRecipes(query);
        }


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
