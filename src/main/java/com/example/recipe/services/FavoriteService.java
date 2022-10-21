package com.example.recipe.services;

import com.example.recipe.models.Favourites;

import java.util.List;

public interface FavoriteService {

    void AddFavorites(Long recipeId);
    void RemoveFavorites(Long recipeId);

    List<Favourites> getUserFavourites();
}
