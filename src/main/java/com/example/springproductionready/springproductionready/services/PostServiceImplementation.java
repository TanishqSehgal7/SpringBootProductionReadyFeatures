package com.example.springproductionready.springproductionready.services;

import com.example.springproductionready.springproductionready.dto.PostDto;
import com.example.springproductionready.springproductionready.entities.Post;
import com.example.springproductionready.springproductionready.exceptions.ResourceNotFoundException;
import com.example.springproductionready.springproductionready.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;
    ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().
                stream().map(postEntity->
                modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id: " + postId));

        return modelMapper.map(post, PostDto.class);
    }
}
