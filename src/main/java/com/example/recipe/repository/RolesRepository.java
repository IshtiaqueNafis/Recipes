package com.example.recipe.repository;

import com.example.recipe.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
