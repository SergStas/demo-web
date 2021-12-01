package com.example.demoweb.service;

import com.example.demoweb.model.Post;
import com.example.demoweb.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {
    @Autowired
    PostsRepository postsRepository;

    public LikesService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public int likes(Long postId)
    {
        Post post = postsRepository.findById(postId).get();
        post.setLikes(post.getLikes() + 1);
        postsRepository.save(post);
        return post.getLikes();
    }
}
