package ca.gbc_assignment.services;

import ca.gbc_assignment.exception.AlreadyOnFavException;
import ca.gbc_assignment.models.Favourites;

import java.util.List;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "Forms contanct for FavoriteServiceImpl"
 */
//endregion
public interface FavoriteService {

    void AddFavorites(Long recipeId) throws AlreadyOnFavException;
    void RemoveFavorites(Long recipeId);

    List<Favourites> getUserFavourites();
}
