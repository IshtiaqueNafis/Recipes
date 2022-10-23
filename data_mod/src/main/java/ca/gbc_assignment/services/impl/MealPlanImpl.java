package ca.gbc_assignment.services.impl;

import ca.gbc_assignment.models.MealPlanner;
import ca.gbc_assignment.models.Recipe;
import ca.gbc_assignment.models.User;
import ca.gbc_assignment.repository.MealPlannerRepository;
import ca.gbc_assignment.repository.RecipeRepository;
import ca.gbc_assignment.repository.UserRepository;
import ca.gbc_assignment.services.MealPlanService;
import ca.gbc_assignment.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MealPlanImpl implements MealPlanService {
    private MealPlannerRepository mealPlannerRepository;
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;

    @Override
    public List<MealPlanner> getMealPlans(LocalDateTime start, LocalDateTime end) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Long UserId = user.getId();
        List<MealPlanner> mealPlan = mealPlannerRepository.findBetween(start, end);
        return mealPlan.stream().filter(u -> u.getUser().getId() == UserId).collect(Collectors.toList());
    }

    @Override
    public void createMealPlan(MealPlanner mealPlanner, Long recipeId, LocalDateTime start, LocalDateTime end) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Recipe recipe = recipeRepository.findById(recipeId).get();
        mealPlanner.setUser(user);
        mealPlanner.setText(recipe.getName());
        mealPlanner.setStart(start);
        mealPlanner.setEnd(end);
        mealPlanner.setRecipe(recipe);
        mealPlannerRepository.save(mealPlanner);

    }


    @Override
    public void updateMealPlan(MealPlanner mealPlanner) {
        mealPlannerRepository.save(mealPlanner);
    }

    @Override
    public MealPlanner moveMealPlanDate(Long id, LocalDateTime start, LocalDateTime end) {
        MealPlanner mealPlanner = mealPlannerRepository.findById(id).get();
        mealPlanner.setStart(start);
        mealPlanner.setEnd(end);
        mealPlannerRepository.save(mealPlanner);
        return mealPlanner;
    }

    @Override
    public MealPlanner updateMealPlan(Long id, Long recipeId) {
        MealPlanner mealPlanner = mealPlannerRepository.findById(id).get();
        mealPlanner.setRecipe(recipeRepository.findById(recipeId).get());
        mealPlanner.setText(recipeRepository.findById(recipeId).get().getName());
        mealPlannerRepository.save(mealPlanner);
        return mealPlanner;
    }

    @Override
    public void deleteMealPlan(Long id) {
        mealPlannerRepository.deleteById(id);
    }

    @Override
    public Long getRecipeInfo(Long id) {
        MealPlanner mealPlanner = mealPlannerRepository.findById(id).get();
        var recipe = recipeRepository.findById(mealPlanner.getRecipe().getId()).get();
        return recipe.getId();

    }


}
