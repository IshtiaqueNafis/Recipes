package com.example.recipe.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque>
 * Student Number: <101206872>
 * Date: October 23, 2022
 * Description: "Recipe model for creating Recipes"
 */
//endregion
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipes")
@Builder
public class Recipe {
    public Recipe(
            @NonNull String name,
            @NonNull String type,
            @NonNull String difficultyLevel,
            @NonNull String photo,
            @NonNull boolean availability,
            @NonNull int calories,
            @NonNull String description,
            User createdBy) {
        this.name = name;
        this.type = type;
        this.difficultyLevel = difficultyLevel;
        this.photo = photo;
        this.availability = availability;
        this.calories = calories;
        this.description = description;
        this.createdBy = createdBy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "recipe_type")
    private String type;

    @NonNull
    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @NonNull
    @Column(name = "photo")
    private String photo;


    @NonNull
    @Column(name = "is_private")
    private boolean availability = false;

    @NonNull
    @Column(name = "calories")
    private int calories;

    @Lob
    @NonNull
    @Column(name = "description")
    private String description;


    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;


    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();


    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE)
    private Set<Favourites> favourites = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;


}
