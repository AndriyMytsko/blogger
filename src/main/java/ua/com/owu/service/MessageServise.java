package ua.com.owu.service;
import ua.com.owu.entity.Message;
import java.util.List;


public interface MessageServise {

    Message findOne(int id);

    List<Message> findAll();

    void save(Message comment);

    List<Message> findMessageOfCurrentUser(int userID);

    void delete(int id);

    }