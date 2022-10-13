package com.example.recipe.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    @NotBlank(message = "name can not be blank")
    private String name;
    @Email
    @NotBlank(message = "email can not be blank")
    private String email;
    @NotBlank(message = "content can not be blank")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
