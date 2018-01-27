package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.entity.Blog;
import ua.com.owu.entity.User;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer>{
    @Query("from User u where u.username =:username")
    public  User findByUserName(@Param("username") String username);

}
