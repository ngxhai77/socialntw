package com.example.socialntw.mapper;

import com.example.socialntw.dto.PostDto;
import com.example.socialntw.entity.Post;
import com.example.socialntw.exception.NotFoundException;
import com.example.socialntw.repository.AreaRepository;
import com.example.socialntw.repository.UserRepository;

public class PostMapper {

    public static Post toEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        return post;
    }

    public static PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setImageUrl(post.getImageUrl());
        postDto.setUserId(post.getUser().getId());
        postDto.setAreaId(post.getArea().getAreaId());
        return postDto;
    }



}