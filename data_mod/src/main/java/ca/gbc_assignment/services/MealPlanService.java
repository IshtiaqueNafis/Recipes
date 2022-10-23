package ca.gbc_assignment.services;

import ca.gbc_assignment.models.MealPlanner;

import java.time.LocalDateTime;
import java.util.List;

public interface MealPlanService {

    List<MealPlanner> getMealPlans(LocalDateTime start, LocalDateTime end);

    void createMealPlan(MealPlanner mealPlanner, Long recipeId, LocalDateTime start, LocalDateTime end);

    void updateMealPlan(MealPlanner mealPlanner);

    MealPlanner moveMealPlanDate(Long id, LocalDateTime start, LocalDateTime end);

    MealPlanner updateMealPlan(Long id, Long recipeId);

    void deleteMealPlan(Long id);

    Long getRecipeInfo(Long id);
}