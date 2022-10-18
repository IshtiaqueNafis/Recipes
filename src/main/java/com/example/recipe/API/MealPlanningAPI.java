package com.example.recipe.API;

import com.example.recipe.models.MealPlanner;
import com.example.recipe.services.MealPlanService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class MealPlanningAPI {

    MealPlanService mealPlanService;

    @GetMapping("/meal_plan")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    List<MealPlanner> getMealPlans(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return mealPlanService.getMealPlans(start,end);
    }


    @PostMapping("/meal_plan/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    MealPlanner createMeal(@NotNull @RequestBody MealPlanCreateParams params) {

        MealPlanner mealPlanner = new MealPlanner();
        System.out.println(mealPlanner.getText());
        System.out.println(mealPlanner.getStart());
        System.out.println(mealPlanner.getEnd());
        mealPlanner.setStart(params.start);
        mealPlanner.setEnd(params.end);
        mealPlanner.setText(params.text);

        mealPlanService.createMealPlan(mealPlanner);
        return mealPlanner;
    }


    public static class MealPlanCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
    }

    public static class MealPlanUpdateParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }

}
