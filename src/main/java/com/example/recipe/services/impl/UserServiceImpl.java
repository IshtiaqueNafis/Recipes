package com.example.recipe.services.impl;

import com.example.recipe.dto.RecipeDto;
import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.exception.AlreadyOnFavException;
import com.example.recipe.exception.EmailAlreadyExistsException;
import com.example.recipe.mapper.RecipeMapper;
import com.example.recipe.models.*;
import com.example.recipe.repository.*;
import com.example.recipe.services.UserService;
import com.example.recipe.utils.RandomImageGenerator;
import com.example.recipe.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
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
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        userDetails.setTotalNumberOfMealPlans(mealPlansCount);
        userDetails.setAverageRecipeRating(Double.parseDouble(df.format(userRatings / counter)));


        return userDetails;
    }

    @Override
    public void changeUserPassword(String OldPassword, String NewPassword) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        if(passwordEncoder.matches(OldPassword,user.getPassword())){
            System.out.println("sucess");
            user.setPassword(passwordEncoder.encode(NewPassword));
            userRepository.save(user);
        }
    }

    @Override
    public void resetPassword() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode("Password1234"));
        userRepository.save(user);

    }

    @Override
    public void changeUserInfo(ChangeUserDetails changeUserDetails) throws EmailAlreadyExistsException {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);

        User checkExistingEmail = userRepository.findByEmail(changeUserDetails.getEmail());

        if(checkExistingEmail==null){
            user.setName(changeUserDetails.getFirstName() + " " + changeUserDetails.getLastName());
            user.setEmail(changeUserDetails.getEmail());
            userRepository.save(user);
        }else{
            throw new EmailAlreadyExistsException();
        }



    }


}
