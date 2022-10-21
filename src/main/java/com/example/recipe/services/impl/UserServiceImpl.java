package com.example.recipe.services.impl;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.mapper.RecipeMapper;
import com.example.recipe.models.*;
import com.example.recipe.repository.*;
import com.example.recipe.services.UserService;
import com.example.recipe.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    RecipeRepository recipeRepository;
    FavouritesRepository favouritesRepository;
    MealPlannerRepository mealPlannerRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = rolesRepository.findByName("REGISTERED");
        System.out.println(role.getName());
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public UserDetails userDetails() {
        UserDetails userDetails = new UserDetails();
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Long UserId = user.getId();
        userDetails.setUserName(user.getName());
        userDetails.setEmail(user.getEmail());
        List<Recipe> recipes = recipeRepository.findRecipesByUser(UserId);
        List<Favourites> favourites = favouritesRepository.findAll();
        List<MealPlanner> mealPlans = (List<MealPlanner>) mealPlannerRepository.findAll();
        List<RecipeDto> recipeDto = recipes.stream().map(recipe -> new RecipeMapper().mapToRecipeDto(recipe)).collect(Collectors.toList());


        int recipeCount = recipes.size();


        userDetails.setTotalRecipesCreated(recipeCount);


        int privateCount = 0;
        for (Recipe recipe : recipes) {
            if (recipe.getCreatedBy().getId() == user.getId() && recipe.isAvailability()) {
                privateCount++;
            }
        }
        userDetails.setTotalPrivateRecipes(privateCount);


        int favCount = 0;

        for (Favourites fav : favourites) {
            if (fav.getUser().getId() == user.getId()) {
                favCount++;
            }
        }

        userDetails.setTotalNumberOfFav(favCount);

        int mealPlansCount = 0;
        for (var mealPlan : mealPlans) {
            if (mealPlan.getUser().getId() == user.getId()) {
                mealPlansCount++;
            }
        }

        double userRatings = 0;
        double counter = 0;
        for (RecipeDto recipe : recipeDto) {
            if (recipe.getCommentRatings() != 0) {
                userRatings += recipe.getCommentRatings();
                counter++;
            }
        }

        userDetails.setTotalNumberOfMealPlans(mealPlansCount);
        userDetails.setAverageRecipeRating(userRatings / counter);
        return userDetails;
    }
}
