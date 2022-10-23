package ca.gbc_assignment.repository;

import ca.gbc_assignment.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "RecipeRepository  for crud operation"
 */
//endregion
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

    Recipe findByName(String name);










    @Override
    Optional<Recipe> findById(Long aLong);
}
