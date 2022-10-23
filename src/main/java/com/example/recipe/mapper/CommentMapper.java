package com.example.recipe.mapper;

import com.example.recipe.dto.CommentDto;
import com.example.recipe.models.Comment;
//region ***** *******************************
/*
 * Project: < project name Recipes >
 * Assignment: < assignment 1 >
 * Author(s): <mahmoud farghali,Nafis Ishtiaque>
 * Student Number: <101347618,101206872>
 * Date: October 23, 2022
 * Description: "Maps Comment to dto and vice versa"
 */
//endregion
public class CommentMapper {
    public static CommentDto mapToCommentDto(Comment comment) {




        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .rating(comment.getRating())
                .updatedOn(comment.getUpdatedOn()).build();
    }

    public static Comment mapToComment(CommentDto commentDto) {

        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .rating(commentDto.getRating())
                .content(commentDto.getContent())
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdatedOn()).build();
    }
}
