package ca.gbc_assignment.services;

import ca.gbc_assignment.dto.RegistrationDto;
import ca.gbc_assignment.models.User;
import ca.gbc_assignment.models.UserDetails;
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
}
