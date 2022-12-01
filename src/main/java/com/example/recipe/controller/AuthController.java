package com.example.recipe.controller;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.models.User;
import com.example.recipe.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    @GetMapping("/register")
    public String registrationForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/uploadimage") public String displayUploadForm() {

        return "registered/myprofile";
    }

    @PostMapping("/upload") public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {

        System.out.println(UPLOAD_DIRECTORY);
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        return "registered/myprofile";
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
