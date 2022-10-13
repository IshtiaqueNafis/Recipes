package com.example.recipe.controller;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.models.User;
import com.example.recipe.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @GetMapping("/register")
    public String registrationForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result,Model model) {

        User existingUser = userService.findByEmail(user.getEmail());

        if(existingUser!=null){
            result.rejectValue("email",null,"email is already taken");

        }
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "/register";
        }

        userService.saveUser(user);
        return "redirect:/";


    }

    @GetMapping("/loginForm")
    public String loginForm(Model model){
        return "login";
    }

}
