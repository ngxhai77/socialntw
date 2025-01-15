package com.example.socialntw.mapper;

import com.example.socialntw.dto.CommentDto;
import com.example.socialntw.entity.Comment;

public class CommentMapper {
    public static Comment toEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        return comment;
    }

    public static CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setContent(comment.getContent());
        commentDto.setPostId(comment.getPost().getPostId());
        return commentDto;
    }
}