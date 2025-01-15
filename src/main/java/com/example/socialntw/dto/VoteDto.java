package com.example.socialntw.dto;

import com.example.socialntw.enums.VoteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private Integer postId;
    private Integer commentId;
    private VoteType voteType;

    // Getters and Setters
}