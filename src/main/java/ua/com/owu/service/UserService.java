package ua.com.owu.service;

import org.springframework.data.repository.query.Param;
import ua.com.owu.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findByName(String username);

    List<User> findAll();

    User findOne(int id);

}
