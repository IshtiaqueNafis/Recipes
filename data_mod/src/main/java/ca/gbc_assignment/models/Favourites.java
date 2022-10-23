package ca.gbc_assignment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque>
 * Student Number: <101347618,101206872>
 * Date: October 23, 2022
 * Description: "Let user save their favourites"
 */
//endregion
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "favourites")
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Favourites(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;


}
