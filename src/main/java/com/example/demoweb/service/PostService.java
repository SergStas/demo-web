package com.example.demoweb.service;

import com.example.demoweb.model.Post;
import com.example.demoweb.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostsRepository postsRepository;

    public Iterable<Post> listAllPosts() {
        return postsRepository.findAll();
    }

    public void create(String text) {
        Post post = new Post(null, text, 0, new Date());
        postsRepository.save(post);
    }
}
