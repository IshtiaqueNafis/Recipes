package com.example.recipe.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): < wendellkeith salting>
 * Student Number: <101271842>
 * Date: October 23, 2022
 * Description: "Map comment before saving and retriving to database"
 */
//endregion
public class CommentDto {
    private Long id;
    @NotBlank(message = "name can not be blank")
    private String name;

    @NotBlank(message = "content can not be blank")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @Positive
    private int rating;
}
