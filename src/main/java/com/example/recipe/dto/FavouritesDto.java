package com.example.recipe.dto;

import com.example.recipe.models.Recipe;
import com.example.recipe.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque, wendellkeith salting>
 * Student Number: <101206872,101271842>
 * Date: October 23, 2022
 * Description: "Map comment before saving and retriving to database"
 */
//endregion
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouritesDto {
    private Long id;
    private User user;
    private Recipe recipe;

}
