package com.example.recipe.API;

import com.example.recipe.models.MealPlanner;
import com.example.recipe.services.MealPlanService;
import com.example.recipe.services.RecipeService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): < Nafis Ishtiaque>
 * Student Number: < 101206872 … >
 * Date: June 23,2022
 * Description: "Adds meal plan to the calendar moves it and deletes it"
 */

//endregion
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class MealPlanningAPI {

    MealPlanService mealPlanService;
    RecipeService recipeService;

    @GetMapping("/meal_plan")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonBackReference
    List<MealPlanner> getMealPlans(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return mealPlanService.getMealPlans(start, end);
    }


    @PostMapping("/meal_plan/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    @JsonBackReference
    MealPlanner createMeal(@NotNull @RequestBody MealPlanCreateParams params, Model model) {

        MealPlanner mealPlanner = new MealPlanner();
        System.out.println(params.recipeId);
        System.out.println(params.start);
        System.out.println(params.end);
        mealPlanService.createMealPlan(mealPlanner, params.recipeId, params.start, params.end);

        return mealPlanner;
    }

    @PostMapping("/meal_plan/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    MealPlanner moveMeal(@RequestBody MealMoveParams params) {
        MealPlanner mealPlanner = mealPlanService.moveMealPlanDate(params.id, params.start, params.end);



        return mealPlanner;
    }

    @PostMapping("/meal_plan/update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    MealPlanner updateMealPlan(@RequestBody MealUpdateParams params) {
        MealPlanner mealPlanner = mealPlanService.updateMealPlan(params.id, params.recipeId);

        return mealPlanner;
    }

    @PostMapping("/meal_plan/delete")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    ResponseEntity<HttpStatus> deleteMealPlan(@RequestBody MealRetrievalParams params) {
        mealPlanService.deleteMealPlan(params.id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

    @PostMapping("/meal_plan/get_recipe_info")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Long getRecipeDetails(@RequestBody MealRetrievalParams params) {
        return mealPlanService.getRecipeInfo(params.id);


    }


    //region *** classes for updating recipes ***
    public static class MealPlanCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public Long recipeId;
        public String text;

    }

    public static class MealPlanUpdateParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }



    public static class MealMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }

    public static class MealUpdateParams {
        public Long id;
        public Long recipeId;

    }

    public static class MealRetrievalParams {
        public Long id;
        public long userId;
        public long recipeId;
    }
    //endregion

}
