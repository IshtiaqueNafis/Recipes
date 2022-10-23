package ca.gbc_assignment.services.impl;

import ca.gbc_assignment.exception.AlreadyOnFavException;
import ca.gbc_assignment.models.Favourites;
import ca.gbc_assignment.models.Recipe;
import ca.gbc_assignment.models.User;
import ca.gbc_assignment.repository.FavouritesRepository;
import ca.gbc_assignment.repository.RecipeRepository;
import ca.gbc_assignment.repository.UserRepository;
import ca.gbc_assignment.services.FavoriteService;
import ca.gbc_assignment.utils.SecurityUtils;
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
    public void AddFavorites(Long recipeId) throws AlreadyOnFavException {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Recipe recipe = recipeRepository.findById(recipeId).get();
        var favouritsList = favouritesRepository.findAll();

        for (var favourite :favouritsList){
            if(favourite.getRecipe().getId()==recipeId && favourite.getUser().getId()==user.getId()){
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
            if (fav.getUser().getId() == userId) {
                fav2.add(fav);
            }
        }
        return fav2;

    }


}
