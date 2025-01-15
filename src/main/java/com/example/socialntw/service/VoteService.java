package com.example.socialntw.service;

import com.example.socialntw.dto.VoteDto;
import com.example.socialntw.entity.User;

public interface VoteService {
    void votePost(User user, VoteDto voteDto);
    void voteComment(User user, VoteDto voteDto);
    int getPostVoteScore(Integer postId);
    int getCommentVoteScore(Integer commentId);
}