package ua.com.owu.dao;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.entity.Post;

import java.util.List;

public interface PostDAO extends JpaRepository<Post, Integer> {

    @Query("select p from Post p join fetch  p.category as c where c.id=:id ORDER BY p.date DESC")
    List<Post> postWithCategory(@Param("id") int id);

    @Query("select p from Post p join fetch  p.user as u where u.id=:id ORDER BY p.date DESC")
    List<Post> postWithUser(@Param("id") int id);

    @Query("select p from Post p ORDER BY p.date DESC")
    List<Post> findAll();

    Post findByIdAndTitle(int id, String title);

}
