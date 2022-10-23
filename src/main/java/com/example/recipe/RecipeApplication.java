package com.example.recipe;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.models.Role;
import com.example.recipe.models.User;
import com.example.recipe.repository.RecipeRepository;
import com.example.recipe.repository.RolesRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.utils.RandomImageGenerator;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class RecipeApplication implements CommandLineRunner {


    RecipeRepository recipeRepository;
    RolesRepository rolesRepository;

    UserRepository userRepository;
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
        users.add(new RegistrationDto("Wendell", "Smith", "Wendell@gmail.com", "Password1234"));
        users.add(new RegistrationDto("Naveed", "Jose", "Naveed@gmail.com", "Password1234"));
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


    }


}
