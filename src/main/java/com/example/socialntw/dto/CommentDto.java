package com.example.socialntw.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Integer commentId;
    private String content;
    private Integer postId;
    // Getters and Setters
}