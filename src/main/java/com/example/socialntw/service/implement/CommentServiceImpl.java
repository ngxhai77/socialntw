package com.example.socialntw.service.implement;

import com.example.socialntw.dto.CommentDto;
import com.example.socialntw.entity.Comment;
import com.example.socialntw.entity.Post;
import com.example.socialntw.entity.User;
import com.example.socialntw.exception.NotFoundException;
import com.example.socialntw.mapper.CommentMapper;
import com.example.socialntw.repository.CommentRepository;
import com.example.socialntw.repository.PostRepository;
import com.example.socialntw.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto addComment(User user, CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new NotFoundException("Post not found"));
        Comment comment = CommentMapper.toEntity(commentDto);
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return CommentMapper.toDto(comment);
    }

    @Override
    public CommentDto replyToComment(Integer id, CommentDto commentDto) {
        Comment parentComment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        Comment comment = CommentMapper.toEntity(commentDto);
        comment.setParentComment(parentComment);
        commentRepository.save(comment);
        return CommentMapper.toDto(comment);
    }

    @Override
    public CommentDto updateComment(Integer id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        comment.setContent(commentDto.getContent());
        commentRepository.save(comment);
        return CommentMapper.toDto(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        return CommentMapper.toDto(comment);
    }
}