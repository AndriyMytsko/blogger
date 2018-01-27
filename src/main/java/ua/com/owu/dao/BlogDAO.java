package ua.com.owu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.entity.Blog;

import java.util.List;

public interface BlogDAO extends JpaRepository<Blog , Integer> {

    @Query("from Blog b join fetch  b.comments where b.id=:xxx")
    Blog blogWithPosts(@Param("xxx") int id);

}
