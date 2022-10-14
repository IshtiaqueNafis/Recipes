package com.example.recipe.repository;

import com.example.recipe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
