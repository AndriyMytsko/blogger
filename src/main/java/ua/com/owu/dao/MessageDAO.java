package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.entity.Message;

import java.util.List;

public interface MessageDAO extends JpaRepository<Message, Integer>{

    @Query("select m from Message m join fetch  m.user as u where u.id=:id ORDER BY m.date DESC")
    List<Message> messageWithUser(@Param("id") int id);

}
