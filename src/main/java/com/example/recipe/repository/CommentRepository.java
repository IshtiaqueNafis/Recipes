package com.example.recipe.repository;

import com.example.recipe.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <Nafis Ishtiaque,wendellkeith salting,mahmoud farghali,Naveed Jose>
 * Student Number: <101206872,101271842,101347618,101347618 >
 * Date: October 23, 2022
 * Description: "Comment repository for crud operation"
 */
//endregion
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "select c.* from comments c inner join recipes r where c.recipe_id=r.id and r.created_by = :userId",nativeQuery = true)
    List<Comment> findCommentByRecipe(Long userId);

}
