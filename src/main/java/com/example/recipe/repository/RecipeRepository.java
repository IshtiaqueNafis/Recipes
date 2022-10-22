package com.example.recipe.repository;

import com.example.recipe.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByType(String type);


    @Query("SELECT r from Recipe r WHERE  r.name LIKE concat('%',:query,'%') OR  r.description like concat('%',:query,'%') AND r.availability=true"
    )
    List<Recipe> searchRecipes(String query);

    @Query(value = "SELECT * FROM recipes r where r.created_by  = :userId", nativeQuery = true)
    List<Recipe> findRecipesByUser(Long userId);

    @Query(value = "SELECT * FROM recipes r where  r.is_private=false", nativeQuery = true)
    List<Recipe> findRecipesPublic();

    List<Recipe> findByDifficultyLevel(String difficultyLevel);










    @Override
    Optional<Recipe> findById(Long aLong);
}
