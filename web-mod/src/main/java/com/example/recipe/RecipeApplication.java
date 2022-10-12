package com.example.recipe;

import com.example.recipe.models.Recipe;
import com.example.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@AllArgsConstructor
public class RecipeApplication implements CommandLineRunner {


    RecipeRepository recipeRepository;
    public static void main(String[] args) {
        SpringApplication.run(RecipeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Recipe[] recipes = new Recipe[]{
                new Recipe(1L, "tastyy", "google.com", "good food", LocalDateTime.now(), LocalDateTime.now())
        };

        for(Recipe recipe:recipes){
            recipeRepository.save(recipe);
        }

    }


}
