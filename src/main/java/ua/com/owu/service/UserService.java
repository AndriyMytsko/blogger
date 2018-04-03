package ua.com.owu.service;

import org.springframework.data.repository.query.Param;
import ua.com.owu.entity.User;

import java.util.List;

public interface UserService {

    User findOne(int id);

    List<User> findAll();

    User findByName(String username);

    void save(User user);

    void delete(int id);

}
