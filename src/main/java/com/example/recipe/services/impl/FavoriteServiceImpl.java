package com.example.recipe.services.impl;

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

@AllArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    FavouritesRepository favouritesRepository;
    RecipeRepository recipeRepository;
    UserRepository userRepository;


    @Override
    public void AddFavorites(Long recipeId) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Recipe recipe = recipeRepository.findById(recipeId).get();
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
            if (fav.getUser().getId() == userId) {
                fav2.add(fav);
            }
        }
        return fav2;

    }


}
