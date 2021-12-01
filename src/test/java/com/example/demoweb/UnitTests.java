package com.example.demoweb;

import com.example.demoweb.model.Post;
import com.example.demoweb.repository.PostsRepository;
import com.example.demoweb.service.LikesService;
import com.example.demoweb.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTests {

    @MockBean
    private PostsRepository postsRepository;

    @Test
    public void saveMethodInvocationTest() {
        PostService postService = new PostService(postsRepository) ;

        String content = "some content";
        Post post = new Post(null, content, 0, new Date());
        postService.create(content);
        Mockito.verify(postsRepository, Mockito.times(1))
                .save(Mockito.argThat(x ->
                        Objects.equals(x.getId(), post.getId())
                                && Objects.equals(x.getText(), post.getText()))
                );
    }

    @Test
    public void likeIncTest() {
        Post post = new Post(null, "text", 0, new Date());
        Mockito.when(postsRepository.findById(Mockito.any())).thenReturn(Optional.of(post));

        LikesService likesService = new LikesService(postsRepository);

        long oldCnt = post.getLikes();
        likesService.likes(Mockito.any());
        long newCnt = post.getLikes();

        Assert.assertEquals(oldCnt + 1, newCnt);
    }
}