package ua.com.owu.service.impl;

import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.BlogDAO;
import ua.com.owu.entity.Blog;
import ua.com.owu.service.BlogService;

import java.util.List;

@Service()
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDAO blogDAO;

    public void setBlogDAO(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    public void save(Blog blog) {
        if (blog != null && !blog.getTitle().isEmpty()) blogDAO.save(blog);
        }

    public Blog findOne(int id) {
        return blogDAO.findOne(id);
    }

    public List<Blog> findAll() {
        return  blogDAO.findAll();
    }

    public Blog delete(int id) {
        blogDAO.delete(id);
        return null;
    }

    public Blog findBlogWithPost(int id) {
        return blogDAO.blogWithPosts(id);
    }
}
