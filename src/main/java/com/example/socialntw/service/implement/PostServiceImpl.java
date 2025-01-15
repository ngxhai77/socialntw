package com.example.socialntw.service.implement;

import com.example.socialntw.dto.PostDto;
import com.example.socialntw.entity.Post;
import com.example.socialntw.entity.User;
import com.example.socialntw.exception.NotFoundException;
import com.example.socialntw.mapper.PostMapper;
import com.example.socialntw.repository.AreaRepository;
import com.example.socialntw.repository.PostRepository;
import com.example.socialntw.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public PostDto addPost(User user, PostDto postDto) {
        Post post = PostMapper.toEntity(postDto);
        post.setUser(user);
        post.setArea(areaRepository.findById(postDto.getAreaId())
                .orElseThrow(() -> new NotFoundException("Area not found")));
        postRepository.save(post);
        return PostMapper.toDto(post);
    }

    @Override
    public PostDto updatePost(Integer id, PostDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageUrl(postDto.getImageUrl());
        postRepository.save(post);
        return PostMapper.toDto(post);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByTitleContainingOrContentContaining(keyword);
        return posts.stream().map(PostMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
        return PostMapper.toDto(post);
    }

    public List<PostDto> getAllPostByAreaId(Integer id) {
        List<Post> posts = postRepository.findByAreaId(id);
        if (posts.isEmpty()) {
            throw new NotFoundException("Posts not found");
        }
        return posts.stream().map(PostMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByUserId(Integer id) {
        return postRepository.findByUserId(id).stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList());
    }
}