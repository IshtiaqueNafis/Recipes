package ca.gbc_assignment.mapper;

import ca.gbc_assignment.dto.CommentDto;
import ca.gbc_assignment.models.Comment;

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
