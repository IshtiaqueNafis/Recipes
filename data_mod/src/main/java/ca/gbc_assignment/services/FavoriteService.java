package ca.gbc_assignment.services;

import ca.gbc_assignment.exception.AlreadyOnFavException;
import ca.gbc_assignment.models.Favourites;

import java.util.List;

public interface FavoriteService {

    void AddFavorites(Long recipeId) throws AlreadyOnFavException;
    void RemoveFavorites(Long recipeId);

    List<Favourites> getUserFavourites();
}
