package com.example.recipe.services;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.models.User;

import javax.transaction.Transactional;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);


    User findByEmail(String email);
}
