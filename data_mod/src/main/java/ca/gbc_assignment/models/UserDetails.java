package ca.gbc_assignment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetails {
    private String userName;
    private String email;
    private int totalRecipesCreated;
    private int totalPrivateRecipes;
    private int totalNumberOfMealPlans;
    private int totalNumberOfFav;
    private double averageRecipeRating;

}
