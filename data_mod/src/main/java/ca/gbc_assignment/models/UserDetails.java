package ca.gbc_assignment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali>
 * Student Number: <101206872,101271842,101347618 >
 * Date: October 23, 2022
 * Description: "Shows user information details"
 */
//endregion
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetails {
    private String userName;
    private String email;
    private String photos;
    private int totalRecipesCreated;
    private int totalPrivateRecipes;
    private int totalNumberOfMealPlans;
    private int totalNumberOfFav;
    private double averageRecipeRating;

}
