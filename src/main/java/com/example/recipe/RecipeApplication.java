package com.example.recipe;

import com.example.recipe.models.Role;
import com.example.recipe.repository.RecipeRepository;
import com.example.recipe.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class RecipeApplication implements CommandLineRunner {


    RecipeRepository recipeRepository;
    RolesRepository rolesRepository;

    public static void main(String[] args) {
        SpringApplication.run(RecipeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role roles = new Role(1L, "ROLE_REGISTERED");
        rolesRepository.save(roles);
    }


}
