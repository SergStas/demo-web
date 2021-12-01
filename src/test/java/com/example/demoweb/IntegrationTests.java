package com.example.demoweb;

import com.example.demoweb.model.Post;
import com.example.demoweb.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/tests.properties")
@Sql(value = {"/posts_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/posts_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class IntegrationTests {
    @Autowired
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void newPostRedirectionTest() throws Exception {
        this.mockMvc.perform(post("/new").param("text", "some content"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void newPostTextTest() throws Exception {
        String content = "some content";

        this.mockMvc.perform(post("/new").param("text", content));
        List<Post> posts = postService.listAllPosts();

        Assert.assertTrue(posts.size() > 0);
        Assert.assertEquals(content, posts.get(posts.size() - 1).getText());
    }
}