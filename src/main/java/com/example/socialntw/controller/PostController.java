package com.example.socialntw.controller;

import com.example.socialntw.dto.PostDto;
import com.example.socialntw.entity.User;
import com.example.socialntw.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public ResponseEntity<PostDto> addPost(@AuthenticationPrincipal UserDetails userDetails , @RequestBody PostDto postDto) {
        User user = (User) userDetails;
        PostDto createdPost = postService.addPost(user, postDto);
        return ResponseEntity.ok(createdPost);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer id, @RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(id, postDto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam String keyword) {
        List<PostDto> posts = postService.searchPosts(keyword);
        return ResponseEntity.ok(posts);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer id) {
        PostDto post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<List<PostDto>> getPostsByAreaId(@PathVariable Integer id) {
        List<PostDto> posts = postService.getAllPostByAreaId(id);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer id) {
        List<PostDto> posts = postService.getAllPostByUserId(id);
        return ResponseEntity.ok(posts);
    }
}