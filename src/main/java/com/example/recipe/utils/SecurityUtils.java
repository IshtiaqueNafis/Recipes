package com.example.recipe.utils;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

    //region ***** *******************************
    /*
     * Project: < project name Recipes >
     * Assignment: < assignment 1 >
     * Author(s): <Nafis Ishtiaque>
     * Student Number: <101206872 >
     * Date: October 23, 2022
     * Description: "Get the current looged in user profile"
     */
//endregion

    public static User getCurrentUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principle instanceof User) {
            return (User) principle;
        }
        return null;
    }
}
