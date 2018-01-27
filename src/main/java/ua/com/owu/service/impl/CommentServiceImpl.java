package ua.com.owu.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.CommentDAO;
import ua.com.owu.entity.Comment;
import ua.com.owu.service.CommentService;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    public static final Logger LOGGER = Logger.getLogger(CommentServiceImpl.class);

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void save(Comment comment) {
        if (comment != null && !comment.getComment().isEmpty()) {

            commentDAO.save(comment);
            LOGGER.info("object " + comment + " was saved");
        }
    }

    public Comment findOne(int id) {
        return commentDAO.findOne(id);
    }

    public List<Comment> findAll() {
        return commentDAO.findAll();
    }


}
