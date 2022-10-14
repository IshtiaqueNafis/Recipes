package com.example.recipe.services.impl;

import com.example.recipe.dto.RegistrationDto;
import com.example.recipe.models.Role;
import com.example.recipe.models.User;
import com.example.recipe.repository.RolesRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RolesRepository rolesRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = rolesRepository.findByName("REGISTERED");
        System.out.println(role.getName());
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }
}
