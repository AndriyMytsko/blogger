package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.PostDAO;
import ua.com.owu.entity.Post;
import ua.com.owu.service.PostService;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    public void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public void save(Post post) {
         postDAO.save(post);
        }

    public Post findOne(int id) {
        return postDAO.findOne(id);
    }

    public List<Post> findAll() {
        return  postDAO.findAll();
    }

    @Override
    public List<Post> findPostOfCurrentCategory(int categoryID) {
        return postDAO.postWithCategory(categoryID);
    }

    @Override
    public List<Post> findPostOfCurrentUser(int userID) {
        return postDAO.postWithUser(userID);
    }

    @Override
    public void delete(int id) {
        postDAO.delete(id);
    }
}
