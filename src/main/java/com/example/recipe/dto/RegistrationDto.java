package com.example.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque, wendellkeith salting>
 * Student Number: <101206872,101271842>
 * Date: October 23, 2022
 * Description: "Map Registration before saving and retriving to database"
 */
//endregion
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    @NotBlank(message = "first name can not be blank")
    private String firstName;
    @NotBlank(message = "last name can not be blank")
    private String lastName;
    @NotBlank(message = "email can not be blank")
    @Email(message = "input does not meet the requirement for an email")
    @NotBlank(message = "email can not be blank")
    private String email;
    @NotBlank(message = "password can not be blank")
    private String password;
}
