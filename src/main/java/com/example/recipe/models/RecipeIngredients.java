package com.example.recipe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;


@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@IdClass(RecipeIngredientsId.class)
@Getter
@Setter

public class RecipeIngredients implements Serializable {

    @Id
    private Long recipeId;

    @Id
    private Long ingredientsId;

    private boolean required = false;

    private int unit;

    private int quantity;

}
