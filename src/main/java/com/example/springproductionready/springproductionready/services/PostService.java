package com.example.springproductionready.springproductionready.services;
import com.example.springproductionready.springproductionready.dto.PostDto;
import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createPost(PostDto postDto);

    PostDto getPostById(Long postId);

    PostDto updatePost(PostDto postDto, Long postId);
}