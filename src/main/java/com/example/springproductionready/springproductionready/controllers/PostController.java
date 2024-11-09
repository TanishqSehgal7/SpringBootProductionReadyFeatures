package com.example.springproductionready.springproductionready.controllers;

import com.example.springproductionready.springproductionready.dto.PostDto;
import com.example.springproductionready.springproductionready.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping(path = "/{postId}")
    public PostDto getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

}
