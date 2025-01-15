package com.example.socialntw.service;

import com.example.socialntw.dto.PostDto;
import com.example.socialntw.entity.User;

import java.util.List;

public interface PostService {
    PostDto addPost(User user, PostDto postDto);
    PostDto updatePost(Integer id, PostDto postDto);
    void deletePost(Integer id);
    List<PostDto> searchPosts(String keyword);
    PostDto getPostById(Integer id);
    List<PostDto> getAllPostByAreaId(Integer id);
    List<PostDto> getAllPostByUserId(Integer id);
}