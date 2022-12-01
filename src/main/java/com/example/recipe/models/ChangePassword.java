package com.example.recipe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
public class ChangePassword {
    @NotBlank(message = "password can not be blank")
    private String oldPassword;
    @NotBlank(message = "password can not be blank")
    private String newPassword1;
    @NotBlank(message = "password2 can not be blank")
    private String newPassword2;
}
