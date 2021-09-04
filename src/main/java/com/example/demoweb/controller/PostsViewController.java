package com.example.demoweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostsViewController {
    @ResponseBody
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String list() {
        return "This is main page";
    }

    @ResponseBody
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String single(@PathVariable("id") Long id) {
        return "Post #" + id + " page";
    }
}
