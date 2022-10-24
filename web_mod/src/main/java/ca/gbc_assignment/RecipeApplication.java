package ca.gbc_assignment;

import ca.gbc_assignment.dto.RegistrationDto;
import ca.gbc_assignment.models.*;
import ca.gbc_assignment.repository.*;
import ca.gbc_assignment.utils.RandomImageGenerator;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
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

/*
  public MealPlanner(LocalDateTime start, LocalDateTime end, String text, User user, Recipe recipe) {
        this.start = start;
        this.end = end;
        this.text = text;
        this.user = user;
        this.recipe = recipe;
    }
 */


        List<MealPlanner> mealPlanners = new ArrayList<>(Arrays.asList(
                new MealPlanner(LocalDateTime.parse("2022-10-23T11:00:00"), LocalDateTime.parse("2022-10-23T11:30:00"), recipeRepository.findByName("Crispy Fish Sandwich").getName(), userRepository.findByEmail("nafu22@gmail.com"), recipeRepository.findByName("Crispy Fish Sandwich")),
                new MealPlanner(LocalDateTime.parse("2022-10-24T11:00:00"), LocalDateTime.parse("2022-10-24T11:30:00"), recipeRepository.findByName("Ham and Cheese Crepes").getName(), userRepository.findByEmail("nafu22@gmail.com"), recipeRepository.findByName("Ham and Cheese Crepes")),
                new MealPlanner(LocalDateTime.parse("2022-10-25T08:00:00"), LocalDateTime.parse("2022-10-25T08:30:00"), recipeRepository.findByName("Veal Gravy Soup").getName(), userRepository.findByEmail("nafu22@gmail.com"), recipeRepository.findByName("Veal Gravy Soup")),
                new MealPlanner(LocalDateTime.parse("2022-10-25T08:00:00"), LocalDateTime.parse("2022-10-25T08:30:00"), recipeRepository.findByName("Veal Gravy Soup").getName(), userRepository.findByEmail("wendell@gmail.com"), recipeRepository.findByName("Veal Gravy Soup")),
                new MealPlanner(LocalDateTime.parse("2022-10-26T08:00:00"), LocalDateTime.parse("2022-10-26T08:30:00"), recipeRepository.findByName("Firecracker Ribs").getName(), userRepository.findByEmail("wendell@gmail.com"), recipeRepository.findByName("Firecracker Ribs")),
                new MealPlanner(LocalDateTime.parse("2022-10-27T08:00:00"), LocalDateTime.parse("2022-10-27T08:30:00"), recipeRepository.findByName("Prekmurska Gibanica").getName(), userRepository.findByEmail("wendell@gmail.com"), recipeRepository.findByName("Prekmurska Gibanica")),
                new MealPlanner(LocalDateTime.parse("2022-10-27T08:00:00"), LocalDateTime.parse("2022-10-27T08:30:00"), recipeRepository.findByName("Prekmurska Gibanica").getName(), userRepository.findByEmail("naveed@gmail.com"), recipeRepository.findByName("Prekmurska Gibanica"))
        ));

        for (MealPlanner mealPlanner:mealPlanners){
            mealPlannerRepository.save(mealPlanner);
        }


    }


}
