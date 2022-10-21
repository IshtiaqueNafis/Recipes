package com.example.recipe.mapper;

import com.example.recipe.dto.CommentDto;
import com.example.recipe.models.Comment;

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
