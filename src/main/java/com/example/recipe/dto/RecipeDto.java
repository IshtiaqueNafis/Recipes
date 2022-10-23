package com.example.recipe.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {


    private Long id;
    @NotBlank(message = "name can not be blank")
    private String name;
    @NotBlank(message = "description can not be blank")
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private String photo;
    private Set<CommentDto> comments = new HashSet<>();
    @NotBlank
    private String type;
    @NotBlank
    private String difficultyLevel;
    @NotNull
    private boolean availability = false;
    @Positive
    private int calories;


    public double getCommentRatings() {
        double ratings = 0;
        for (var comment : comments) {
            ratings += comment.getRating();
        }

        return comments.size() < 0 ? 0 : ratings / comments.size();
    }


}
