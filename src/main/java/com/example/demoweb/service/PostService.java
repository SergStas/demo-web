package com.example.demoweb.service;

import com.example.demoweb.model.Post;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {
    public List<Post> listAllPosts() {
        return Arrays.asList(
                new Post("United Russia", 340097),
                new Post("LDPR", 404),
                new Post("Apple", 12),
                new Post("Other opposition", -2)
        );
    }
}
