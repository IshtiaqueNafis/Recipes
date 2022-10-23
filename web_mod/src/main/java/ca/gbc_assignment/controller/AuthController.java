package ca.gbc_assignment.controller;


import ca.gbc_assignment.dto.RegistrationDto;
import ca.gbc_assignment.models.User;
import ca.gbc_assignment.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): < Nafis Ishtiaque,Naveen Jose>
 * Student Number: < 101206872,101238395>
 * Date: October 23, 2022
 * Description: "let user register and login"
 */
//endregion
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
        return "redirect:/auth/register?success";


    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

}
