package com.example.recipe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class ChangeUserDetails {
    @NotBlank(message = "firstname can not be blank")
    private String firstName;

    @NotBlank(message = "lastname can not be blank")
    private String lastName;

    @NotBlank(message = "Email can not be blank")
    @Email
    private String email;
}
