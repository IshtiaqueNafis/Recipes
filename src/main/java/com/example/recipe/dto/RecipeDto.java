package com.example.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {


    private Long id;
    @NotBlank(message = "name can not be blank")
    private String name;
    private String url;
    @NotBlank(message = "description can not be blank")
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
