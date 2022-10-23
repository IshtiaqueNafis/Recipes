package ca.gbc_assignment.services.impl;

import ca.gbc_assignment.dto.RecipeDto;
import ca.gbc_assignment.dto.RegistrationDto;
import ca.gbc_assignment.models.*;
import ca.gbc_assignment.mapper.RecipeMapper;
import ca.gbc_assignment.repository.*;
import ca.gbc_assignment.services.UserService;
import ca.gbc_assignment.utils.RandomImageGenerator;
import ca.gbc_assignment.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "Implements all crud operation for UserService "
 */
//endregion
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
        user.setPhoto(RandomImageGenerator.randomPersonHolder.get(RandomImageGenerator.randomImageGeneratorPerson()));
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
        userDetails.setPhotos(user.getPhoto());
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
