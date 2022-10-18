package com.example.recipe.services.impl;

import com.example.recipe.models.MealPlanner;
import com.example.recipe.repository.MealPlannerRepository;
import com.example.recipe.services.MealPlanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class MealPlanImpl implements MealPlanService {
    private MealPlannerRepository mealPlannerRepository;

    @Override
    public List<MealPlanner> getMealPlans(LocalDateTime start, LocalDateTime end) {
      return (List<MealPlanner>) mealPlannerRepository.findBetween(start,end);
    }

    @Override
    public void createMealPlan(MealPlanner mealPlanner) {
        mealPlannerRepository.save(mealPlanner);
    }



    @Override
    public void updateMealPlan(MealPlanner mealPlanner) {
         mealPlannerRepository.save(mealPlanner);
    }


}
