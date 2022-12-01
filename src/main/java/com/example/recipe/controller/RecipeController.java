package com.example.recipe.controller;

import com.example.recipe.dto.CommentDto;
import com.example.recipe.dto.RecipeDto;
import com.example.recipe.exception.AlreadyOnFavException;
import com.example.recipe.exception.EmailAlreadyExistsException;
import com.example.recipe.exception.NotFoundException;
import com.example.recipe.models.ChangePassword;
import com.example.recipe.models.ChangeUserDetails;
import com.example.recipe.models.User;
import com.example.recipe.models.UserDetails;
import com.example.recipe.services.CommentService;
import com.example.recipe.services.FavoriteService;
import com.example.recipe.services.RecipeService;
import com.example.recipe.services.UserService;
import com.example.recipe.utils.SelectOptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;


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


    private final String UPLOAD_DIR = "src/main/resources/static/uploads";

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
        User user = userService.findByEmail(usersDetails.getEmail());
        model.addAttribute("user", user);
        model.addAttribute("changePassword", new ChangePassword());
        model.addAttribute("changeUserDetails", new ChangeUserDetails());



        String ratings = "";
        if (usersDetails.getAverageRecipeRating() > 0) {
            ratings = String.valueOf(usersDetails.getAverageRecipeRating());

        } else {
            ratings = "No ratings yet";
        }
        model.addAttribute("ratings", ratings);
        return "registered/myprofile";
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Model model) throws IOException {


        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:registered/recipes/myprofile";
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path path = Paths.get(UPLOAD_DIR + fileName);
        System.out.println(fileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        attributes.addFlashAttribute("message", "File is Uploading please wait");

        return "redirect:/registered/recipes/myprofile";


    }

    // public String createRecipe(@Valid @ModelAttribute("recipe") RecipeDto recipeDto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    @PostMapping("/changeUserPassword")
    public String changeUserInfo(@Valid @ModelAttribute("changePassword") ChangePassword changePassword, BindingResult result) {

        if (result.hasErrors()) {

            return "registered/myprofile";
        }

        userService.changeUserPassword(changePassword.getOldPassword(), changePassword.getNewPassword1());

        return "redirect:/logout";
    }

    @GetMapping("/resetPassword")
    public String ResetPassword(){
        userService.resetPassword();
        return "redirect:/logout";
    }

    @PostMapping("/changeUserDetails")
    public String changeUserDetails(@Valid @ModelAttribute("changeUserDetails")ChangeUserDetails changeUserDetails,BindingResult result){
        if (result.hasErrors()) {

            return "registered/myprofile";
        }
        try {
            userService.changeUserInfo(changeUserDetails);
        } catch (EmailAlreadyExistsException e) {
            return "error/EmailAlreadyExisitsError";
        }

        return "redirect:/logout";
    }



}




