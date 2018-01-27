package ua.com.owu.service;

import org.springframework.data.jpa.repository.Query;
import ua.com.owu.entity.Blog;

import java.util.List;

public interface BlogService {

    void save(Blog blog);

    Blog findOne(int id);

    List<Blog> findAll();

    Blog delete(int id);

    Blog findBlogWithPost(int id);

}
