package com.example.recipe.controller;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.models.Role;
import com.example.recipe.models.User;
import com.example.recipe.repository.RolesRepository;
import com.example.recipe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private RolesRepository rolesRepository;
    private UserRepository userRepository;

    @GetMapping("/register")
    public String registrationForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }

//    @PostMapping("/register/save")
//    public String registerUser(RegistrationDto registrationDto){
//        User user = new User();
//        user.setName(registrationDto.getFirstName()+ " " + registrationDto.getLastName());
//        user.setEmail(registrationDto.getEmail());
//        user.setPassword(registrationDto.getPassword());
//        Role role = rolesRepository.findByName("ROLE_REGISTERED");
//        user.setRoles(Arrays.asList(role));
//        userRepository.save(user);
//
//
//
//
//    }

}
