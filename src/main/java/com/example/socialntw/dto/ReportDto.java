package com.example.socialntw.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {

    private Integer userId;
    private Integer postId;
    private Integer commentId;
    private Integer areaId;
    private String reason;
}
