package com.example.recipe.dto;

import com.example.recipe.models.Recipe;
import com.example.recipe.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouritesDto {
    private Long id;
    private User user;
    private Recipe recipe;

}
