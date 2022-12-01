package com.example.recipe.repository;

import com.example.recipe.models.Events;
import com.example.recipe.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Long> {
    long countByRecipe_Id(Long id);







}