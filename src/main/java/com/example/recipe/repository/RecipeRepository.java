package com.example.recipe.repository;

import com.example.recipe.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByUrl(String url);

    @Query("SELECT r from Recipe r WHERE  r.name LIKE concat('%',:query,'%') OR  r.description like concat('%',:query,'%')"
    )
    List<Recipe> searchRecipes(String query);

    @Query(value = "SELECT * FROM recipes r where r.created_by  = :userId" ,nativeQuery = true)
    List<Recipe>findRecipesByUser(Long userId);


}
