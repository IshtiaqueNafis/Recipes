package com.example.recipe.services;

import com.example.recipe.models.MealPlanner;

import java.time.LocalDateTime;
import java.util.List;

public interface MealPlanService {

    List<MealPlanner> getMealPlans(LocalDateTime start, LocalDateTime end);
   void createMealPlan(MealPlanner mealPlanner);

    void updateMealPlan(MealPlanner mealPlanner);
}
