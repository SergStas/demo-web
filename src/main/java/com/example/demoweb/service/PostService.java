package com.example.demoweb.service;

import com.example.demoweb.model.Post;
import com.example.demoweb.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    @Autowired
    PostsRepository postsRepository;

    public PostService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public List<Post> listAllPosts() {
        return StreamSupport
                .stream(postsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void create(String text) {
        Post post = new Post(null, text, 0, new Date());
        postsRepository.save(post);
    }
}
