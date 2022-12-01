package com.example.recipe.services.impl;

import com.example.recipe.exception.AlreadyOnFavException;
import com.example.recipe.models.Favourites;
import com.example.recipe.models.Recipe;
import com.example.recipe.models.User;
import com.example.recipe.repository.FavouritesRepository;
import com.example.recipe.repository.RecipeRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.services.FavoriteService;
import com.example.recipe.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "Implements all crud operation for FavoriteService"
 */
//endregion
@AllArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    FavouritesRepository favouritesRepository;
    RecipeRepository recipeRepository;
    UserRepository userRepository;


    @Override
    public void AddFavorites(Long recipeId) throws AlreadyOnFavException {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Recipe recipe = recipeRepository.findById(recipeId).get();
        var favouriteList = favouritesRepository.findAll();

        for (var favourite :favouriteList){
            if(Objects.equals(favourite.getRecipe().getId(), recipeId) && Objects.equals(favourite.getUser().getId(), user.getId())){
                throw new AlreadyOnFavException();
            }
        }

        Favourites favourites = new Favourites();
        favourites.setUser(user);
        favourites.setRecipe(recipe);
        favouritesRepository.save(favourites);


    }

    @Override
    public void RemoveFavorites(Long id) {
     favouritesRepository.deleteById(id);

    }

    @Override
    public List<Favourites> getUserFavourites() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Long userId = user.getId();

        var fav1 = favouritesRepository.findAll();
        List<Favourites> fav2 = new ArrayList<>();
        for (var fav : fav1) {
            if (Objects.equals(fav.getUser().getId(), userId)) {
                fav2.add(fav);
            }
        }
        return fav2;

    }


}
