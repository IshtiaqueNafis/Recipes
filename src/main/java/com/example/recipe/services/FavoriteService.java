package com.example.recipe.services;

import com.example.recipe.exception.AlreadyOnFavException;
import com.example.recipe.models.Favourites;

import java.util.List;

public interface FavoriteService {

    void AddFavorites(Long recipeId) throws AlreadyOnFavException;
    void RemoveFavorites(Long recipeId);

    List<Favourites> getUserFavourites();
}
