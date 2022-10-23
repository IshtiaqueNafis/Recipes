package com.example.recipe.repository;

import com.example.recipe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "UserRepository   for crud operation"
 */
//endregion
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
