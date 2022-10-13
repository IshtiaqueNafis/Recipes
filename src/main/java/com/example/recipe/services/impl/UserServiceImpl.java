package com.example.recipe.services.impl;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.models.Role;
import com.example.recipe.models.User;
import com.example.recipe.repository.RolesRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RolesRepository rolesRepository;

    @Override
    public void saveUser(RegistrationDto registrationDto) {
                User user = new User();
        user.setName(registrationDto.getFirstName()+ " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = rolesRepository.findByName("ROLE_REGISTERED");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
