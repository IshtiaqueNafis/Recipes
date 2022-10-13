package com.example.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private Set<CommentDto> comments= new HashSet<>();

}
