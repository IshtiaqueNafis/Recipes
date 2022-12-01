package com.example.recipe.services;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.exception.EmailAlreadyExistsException;
import com.example.recipe.models.ChangeUserDetails;
import com.example.recipe.models.User;
import com.example.recipe.models.UserDetails;

import javax.transaction.Transactional;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "Forms contanct for UserServiceImpl"
 */
//endregion
public interface UserService {
    void saveUser(RegistrationDto registrationDto);


    User findByEmail(String email);

    UserDetails userDetails();

    void changeUserPassword(String OldPassword, String NewPassword);

    void resetPassword();

    void changeUserInfo(ChangeUserDetails changeUserDetails) throws EmailAlreadyExistsException;
}
