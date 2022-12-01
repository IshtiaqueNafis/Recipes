package com.example.recipe.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class RecipeIngredientsId implements Serializable {

    private Long recipeId;
    private Long ingredientsId;
}
