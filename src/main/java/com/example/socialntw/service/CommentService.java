package com.example.socialntw.service;

import com.example.socialntw.dto.CommentDto;
import com.example.socialntw.entity.User;

public interface CommentService {
    CommentDto addComment(User user, CommentDto commentDto);
    CommentDto replyToComment(Integer id, CommentDto commentDto);
    CommentDto updateComment(Integer id, CommentDto commentDto);
    void deleteComment(Integer id);

    CommentDto getCommentById(Integer commentId);
}