package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.entity.Comment;

import java.util.List;

public interface CommentDAO  extends JpaRepository<Comment, Integer>{

    @Query("select c from Comment c join fetch  c.post as p where p.id=:id ORDER BY c.date DESC")
    List<Comment> postWithComment(@Param("id") int id);

}
