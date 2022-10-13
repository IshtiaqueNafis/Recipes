package com.example.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
