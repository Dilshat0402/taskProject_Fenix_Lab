package com.dilshat.blog_project.services.impl;

import com.dilshat.blog_project.models.Post;
import com.dilshat.blog_project.models.User;
import com.dilshat.blog_project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(new Post(0L, "NO TITLE", "NO CONTENT", new Timestamp(System.currentTimeMillis()), null, new User()));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
