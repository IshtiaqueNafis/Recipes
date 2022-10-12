package com.example.recipe;

import com.example.recipe.models.Recipe;
import com.example.recipe.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
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


    }


}
