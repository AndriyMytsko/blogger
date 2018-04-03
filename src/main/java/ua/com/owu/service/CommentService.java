package ua.com.owu.service;

import ua.com.owu.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment findOne(int id);

    List<Comment> findAll();

    void save(Comment comment);

    List<Comment> findCommentOfCurrentCategory(int categoryID);

    Comment delete(int id);


}
