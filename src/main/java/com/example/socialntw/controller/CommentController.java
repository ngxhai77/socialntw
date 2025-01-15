package com.example.socialntw.controller;

import com.example.socialntw.dto.CommentDto;
import com.example.socialntw.entity.User;
import com.example.socialntw.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<CommentDto> addComment(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CommentDto commentDto) {
        User user = (User) userDetails;
        CommentDto createdComment = commentService.addComment(user,commentDto);
        return ResponseEntity.ok(createdComment);
    }

    @PostMapping("/reply/{id}")
    public ResponseEntity<CommentDto> replyToComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.replyToComment(id, commentDto);
        return ResponseEntity.ok(createdComment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(id, commentDto);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}