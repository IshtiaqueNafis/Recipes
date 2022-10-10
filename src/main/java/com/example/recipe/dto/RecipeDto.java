package com.example.recipe.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class RecipeDto {
    private Long id;
    private String name;
    private String url;
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
