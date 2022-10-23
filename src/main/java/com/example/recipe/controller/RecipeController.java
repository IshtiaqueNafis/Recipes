package com.example.recipe.controller;

import com.example.recipe.dto.CommentDto;
import com.example.recipe.dto.RecipeDto;
import com.example.recipe.exception.AlreadyOnFavException;
import com.example.recipe.exception.NotFoundException;
import com.example.recipe.models.UserDetails;
import com.example.recipe.services.CommentService;
import com.example.recipe.services.FavoriteService;
import com.example.recipe.services.RecipeService;
import com.example.recipe.services.UserService;
import com.example.recipe.utils.SelectOptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;


//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): < Nafis Ishtiaque,Naveen Jose mahmoud farghali,wendellkeith salting>
 * Student Number: < 101206872,101238395,101347618,101271842>
 * Date: October 23, 2022
 * Description: "Recipe let users create edit or private recipes non registered user can also use this to veiw recipes as well as comment section is only open for non registered user"
 */
//endregion

@AllArgsConstructor
@Controller

public class RecipeController {
    private RecipeService recipeService;
    private CommentService commentService;

    private UserService userService;
    private FavoriteService favoriteService;


    @GetMapping(value = {"/registered/recipe_form", "registered/recipe_form/{id}"})
    public String newRecipeForm(Model model, @PathVariable(value = "id", required = false) Long id) {

        try {
            model.addAttribute("recipe", recipeService.findRecipeById(id));
            model.addAttribute("difficulties", SelectOptions.DIFFICULTY);
            model.addAttribute("types", SelectOptions.MEALTYPE);
            return "registered/recipe_form";
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //handler method to handle submit request
    @PostMapping("/registered/submit_recipe")
    public String createRecipe(@Valid @ModelAttribute("recipe") RecipeDto recipeDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("recipe", recipeDto);
            model.addAttribute("difficulties", SelectOptions.DIFFICULTY);
            model.addAttribute("types", SelectOptions.MEALTYPE);
            return "registered/recipe_form";
        }

        recipeService.submitRecipe(recipeDto);


        return "redirect:/registered/recipes";


    }


    //show list of comment request.

    @GetMapping("/registered/comments")
    public String recipeComments(Model model) {
        List<CommentDto> comments = commentService.findCommentByRecipes();
        model.addAttribute("comments", comments);
        return "registered/comments";

    }

    @GetMapping(path = {"/registered/recipes"})
    public String searchPosts(Model model) {
        List<RecipeDto> recipes;
        recipes = recipeService.findRecipeByUser();
        model.addAttribute("recipes", recipes);

        return "registered/recipes";
    }

    @GetMapping("/registered/recipes/comment/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "registered/comments";
    }

    @GetMapping("/registered/recipes/av/{id}")
    public String makeRecipePublicOrPrivate(@PathVariable("id") long id) {
        RecipeDto recipeDto = null;
        try {
            recipeDto = recipeService.findRecipeById(id);
            recipeDto.setAvailability(!recipeDto.isAvailability());
            if (recipeDto.isAvailability()) {
                recipeService.updateRecipeForOtherUser(recipeDto.getId());
            }
            recipeService.updateRecipe(recipeDto);
            return "redirect:/registered/recipes";
        } catch (NotFoundException e) {
            return "error";
        }


    }

    @GetMapping("/registered/recipes/favourites")
    public String getRecipeFavourites(Model model) {
        var fav = favoriteService.getUserFavourites();
        model.addAttribute("fav", fav);
        return "registered/favourites";
    }

    @GetMapping("/registered/recipes/favourites/add/{id}")
    public String AddToFavourites(@PathVariable("id") long id) {
        try {
            favoriteService.AddFavorites(id);
            return "redirect:/registered/recipes/favourites";
        } catch (AlreadyOnFavException e) {
            return "error/RecipeDuplicateError";
        }

    }

    @GetMapping("/registered/recipes/favourites/remove/{id}")
    public String RemoveFromFavourites(@PathVariable("id") long id) {

        try {
            favoriteService.RemoveFavorites(id);
            return "redirect:/registered/recipes/favourites";
        } catch (Error e) {
            return "error";
        }

    }

    @GetMapping("/registered/recipes/myprofile")
    public String MyProfile(Model model) {
        UserDetails usersDetails = userService.userDetails();
        model.addAttribute("userDetails", usersDetails);
        String ratings = "";
        if (usersDetails.getAverageRecipeRating() > 0) {
            ratings = String.valueOf(usersDetails.getAverageRecipeRating());

        } else {
            ratings = "No ratings yet";
        }
        model.addAttribute("ratings", ratings);
        return "registered/myprofile";
    }


}
