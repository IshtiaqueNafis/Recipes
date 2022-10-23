package com.example.recipe;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.models.*;
import com.example.recipe.repository.*;
import com.example.recipe.utils.RandomImageGenerator;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque>
 * Student Number: <101206872 >
 * Date: October 23, 2022
 * Description: "Application for running the program"
 */
//endregion
@SpringBootApplication
@AllArgsConstructor
public class RecipeApplication implements CommandLineRunner {


    RecipeRepository recipeRepository;
    RolesRepository rolesRepository;

    UserRepository userRepository;
    FavouritesRepository favouritesRepository;
    CommentRepository commentRepository;
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(RecipeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role roles = new Role(1L, "REGISTERED");
        rolesRepository.save(roles);

        List<RegistrationDto> users = new ArrayList<>();

        users.add(new RegistrationDto("nafis", "ishtiaque", "nafu22@gmail.com", "Password1234"));
        users.add(new RegistrationDto("Wendell", "Smith", "wendell@gmail.com", "Password1234"));
        users.add(new RegistrationDto("Naveed", "Jose", "naveed@gmail.com", "Password1234"));
        long id = 2;
        for (RegistrationDto registrationDto : users) {
            User user = new User();
            user.setId(id);
            user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
            user.setEmail(registrationDto.getEmail());
            user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
            user.setRoles(Arrays.asList(roles));
            user.setPhoto(RandomImageGenerator.randomPersonHolder.get(RandomImageGenerator.randomImageGeneratorPerson()));
            userRepository.save(user);
            id++;

        }





        List<Recipe> recipes = new ArrayList<>(
                Arrays.asList(
                        new Recipe("Tandoori Masala", "Breakfast", "Easy", RandomImageGenerator.randomFoodImageHolder.get(RandomImageGenerator.randomImageGeneratorFood()), false, 250, RandomImageGenerator.randomDescription, userRepository.findByEmail("nafu22@gmail.com")),
                        new Recipe("Steak, Bacon, and Gruyere Paninis", "Dinner", "Medium", RandomImageGenerator.randomFoodImageHolder.get(RandomImageGenerator.randomImageGeneratorFood()), false, 1250, RandomImageGenerator.randomDescription, userRepository.findByEmail("nafu22@gmail.com")),
                        new Recipe("Crispy Fish Sandwich", "Lunch", "Hard", RandomImageGenerator.randomFoodImageHolder.get(RandomImageGenerator.randomImageGeneratorFood()), false, 750, RandomImageGenerator.randomDescription, userRepository.findByEmail("nafu22@gmail.com")),
                        new Recipe("Potato Samosas (Aloo Pies)", "Lunch", "Hard", RandomImageGenerator.randomFoodImageHolder.get(RandomImageGenerator.randomImageGeneratorFood()), false, 1750, RandomImageGenerator.randomDescription, userRepository.findByEmail("naveed@gmail.com")),
                        new Recipe("Veal Gravy Soup", "Dinner", "Easy", RandomImageGenerator.randomFoodImageHolder.get(RandomImageGenerator.randomImageGeneratorFood()), false, 350, RandomImageGenerator.randomDescription, userRepository.findByEmail("naveed@gmail.com")),
                        new Recipe("Prekmurska Gibanica", "Breakfast", "Easy", RandomImageGenerator.randomFoodImageHolder.get(RandomImageGenerator.randomImageGeneratorFood()), false, 450, RandomImageGenerator.randomDescription, userRepository.findByEmail("naveed@gmail.com")),
                        new Recipe("Princess Cake", "Breakfast", "Easy", RandomImageGenerator.randomFoodImageHolder.get(RandomImageGenerator.randomImageGeneratorFood()), false, 450, RandomImageGenerator.randomDescription, userRepository.findByEmail("wendell@gmail.com")),
                        new Recipe("Ham and Cheese Crepes", "Breakfast", "Easy", RandomImageGenerator.randomFoodImageHolder.get(RandomImageGenerator.randomImageGeneratorFood()), false, 450, RandomImageGenerator.randomDescription, userRepository.findByEmail("wendell@gmail.com")),
                        new Recipe("Firecracker Ribs", "Breakfast", "Easy", RandomImageGenerator.randomFoodImageHolder.get(RandomImageGenerator.randomImageGeneratorFood()), false, 450, RandomImageGenerator.randomDescription, userRepository.findByEmail("wendell@gmail.com"))

                )
        );

        for (Recipe recipe : recipes) {
            recipeRepository.save(recipe);

        }

        commentRepository.save(new Comment("nafis", 1, "Bad Recipe", recipeRepository.findByName("Tandoori Masala")));
        commentRepository.save(new Comment("nafu", 2, "Bad Recipe", recipeRepository.findByName("Tandoori Masala")));
        commentRepository.save(new Comment("naf", 5, "Bad Recipe", recipeRepository.findByName("Tandoori Masala")));


        commentRepository.save(new Comment("nafis", 1, "Bad Recipe", recipeRepository.findByName("Steak, Bacon, and Gruyere Paninis")));
        commentRepository.save(new Comment("nafu", 2, "Bad Recipe", recipeRepository.findByName("Steak, Bacon, and Gruyere Paninis")));
        commentRepository.save(new Comment("naf", 5, "Bad Recipe", recipeRepository.findByName("Steak, Bacon, and Gruyere Paninis")));


        commentRepository.save(new Comment("nafis", 1, "Bad Recipe", recipeRepository.findByName("Crispy Fish Sandwich")));
        commentRepository.save(new Comment("nafu", 2, "Bad Recipe", recipeRepository.findByName("Potato Samosas (Aloo Pies)")));
        commentRepository.save(new Comment("naf", 5, "Bad Recipe", recipeRepository.findByName("Veal Gravy Soup")));
        commentRepository.save(new Comment("naf", 5, "Bad Recipe", recipeRepository.findByName("Prekmurska Gibanica")));
        commentRepository.save(new Comment("naf", 5, "Bad Recipe", recipeRepository.findByName("Princess Cake")));
        commentRepository.save(new Comment("naf", 5, "Bad Recipe", recipeRepository.findByName("Ham and Cheese Crepes")));
        commentRepository.save(new Comment("naf", 5, "Bad Recipe", recipeRepository.findByName("Firecracker Ribs")));




        favouritesRepository.save(new Favourites(userRepository.findByEmail("nafu22@gmail.com"), recipeRepository.findByName("Tandoori Masala")));
        favouritesRepository.save(new Favourites(userRepository.findByEmail("nafu22@gmail.com"), recipeRepository.findByName("Steak, Bacon, and Gruyere Paninis")));
        favouritesRepository.save(new Favourites(userRepository.findByEmail("nafu22@gmail.com"), recipeRepository.findByName("Firecracker Ribs")));
        favouritesRepository.save(new Favourites(userRepository.findByEmail("wendell@gmail.com"), recipeRepository.findByName("Veal Gravy Soup")));
        favouritesRepository.save(new Favourites(userRepository.findByEmail("wendell@gmail.com"), recipeRepository.findByName("Princess Cake")));
        favouritesRepository.save(new Favourites(userRepository.findByEmail("naveed@gmail.com"), recipeRepository.findByName("Ham and Cheese Crepes")));
        favouritesRepository.save(new Favourites(userRepository.findByEmail("naveed@gmail.com"), recipeRepository.findByName("Veal Gravy Soup")));
        favouritesRepository.save(new Favourites(userRepository.findByEmail("naveed@gmail.com"), recipeRepository.findByName("Crispy Fish Sandwich")));




    }


}
