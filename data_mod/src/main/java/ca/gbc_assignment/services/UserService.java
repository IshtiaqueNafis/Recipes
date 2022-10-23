package ca.gbc_assignment.services;

import ca.gbc_assignment.dto.RegistrationDto;
import ca.gbc_assignment.models.User;
import ca.gbc_assignment.models.UserDetails;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);


    User findByEmail(String email);

    UserDetails userDetails();
}
