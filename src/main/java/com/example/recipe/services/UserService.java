package com.example.recipe.services;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.models.User;
import com.example.recipe.models.UserDetails;

import javax.transaction.Transactional;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);


    User findByEmail(String email);

    UserDetails userDetails();
}
