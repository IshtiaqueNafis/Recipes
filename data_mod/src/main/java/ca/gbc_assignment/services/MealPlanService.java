package ca.gbc_assignment.services;

import ca.gbc_assignment.models.MealPlanner;

import java.time.LocalDateTime;
import java.util.List;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "Forms contanct for MealPlanServiceImpl"
 */
//endregion
public interface MealPlanService {

    List<MealPlanner> getMealPlans(LocalDateTime start, LocalDateTime end);

    void createMealPlan(MealPlanner mealPlanner, Long recipeId, LocalDateTime start, LocalDateTime end);

    void updateMealPlan(MealPlanner mealPlanner);

    MealPlanner moveMealPlanDate(Long id, LocalDateTime start, LocalDateTime end);

    MealPlanner updateMealPlan(Long id, Long recipeId);

    void deleteMealPlan(Long id);

    Long getRecipeInfo(Long id);
}
