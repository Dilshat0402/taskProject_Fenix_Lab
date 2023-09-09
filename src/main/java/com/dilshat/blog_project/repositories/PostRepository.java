package com.dilshat.blog_project.repositories;

import com.dilshat.blog_project.models.Post;
import com.dilshat.blog_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTitle(String title);

    List<Post> findAllByAuthor(User user);


}
