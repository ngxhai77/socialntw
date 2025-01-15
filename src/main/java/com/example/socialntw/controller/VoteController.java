package com.example.socialntw.controller;

import com.example.socialntw.dto.VoteDto;
import com.example.socialntw.entity.User;
import com.example.socialntw.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/post")
    public ResponseEntity<Void> votePost(@AuthenticationPrincipal UserDetails userDetails, @RequestBody VoteDto voteDto) {
        User user = (User) userDetails;
        voteService.votePost(user, voteDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comment")
    public ResponseEntity<Void> voteComment(@AuthenticationPrincipal UserDetails userDetails, @RequestBody VoteDto voteDto) {
        User user = (User) userDetails;
        voteService.voteComment(user, voteDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/post/{postId}/score")
    public ResponseEntity<Integer> getPostVoteScore(@PathVariable Integer postId) {
        int score = voteService.getPostVoteScore(postId);
        return ResponseEntity.ok(score);
    }

    @GetMapping("/comment/{commentId}/score")
    public ResponseEntity<Integer> getCommentVoteScore(@PathVariable Integer commentId) {
        int score = voteService.getCommentVoteScore(commentId);
        return ResponseEntity.ok(score);
    }
}