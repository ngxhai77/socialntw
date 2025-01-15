package com.example.socialntw.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageUrl;
    private Integer userId;
    private Integer areaId;
}