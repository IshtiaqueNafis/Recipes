package com.example.recipe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <wendellkeith salting>
 * Student Number: <101271842>
 * Date: October 23, 2022
 * Description: "Meal plan for the user"
 */
//endregion
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "meal_planner")
public class MealPlanner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "start_time")
    private LocalDateTime start;
    @Column(name = "end_time")
    private LocalDateTime end;
    private String text;

    public MealPlanner(LocalDateTime start, LocalDateTime end, String text, User user, Recipe recipe) {
        this.start = start;
        this.end = end;
        this.text = text;
        this.user = user;
        this.recipe = recipe;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

//    @ManyToOne
//    @JoinColumn(name = "recipe_id", nullable = false)
//    private Recipe recipe;
}
