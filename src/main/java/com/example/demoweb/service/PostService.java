package com.example.demoweb.service;

import com.example.demoweb.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
    private List<Post> posts;

    public PostService() {
        this.posts = new ArrayList<>();
        posts.add(new Post(0, "Post 5675671", 1, new Date(1345647)));
        posts.add(new Post(1, "Post 5675671", 2, new Date(1573457)));
        posts.add(new Post(2, "Post 5675671", 0, new Date(14357)));
        posts.add(new Post(3, "Post 5675671", 4, new Date(34574561)));
    }

    public List<Post> listAllPosts() {
        return posts;
    }

    public void create(String text) {
        posts.add(new Post(posts.size(), text,1, new Date()));
    }
}
