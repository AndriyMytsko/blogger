package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.UserDAO;
import ua.com.owu.entity.User;
import ua.com.owu.service.UserService;

import java.util.List;
@Service
public class UserServiceImpl implements UserService,UserDetailsService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    public User findByName(String username) {
        return userDAO.findByUserName(username);
    }

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public User findOne(int id){
        return userDAO.findOne(id);
    }


    public void save(String name, String email) {

    }
    public void delete(int id) {
        userDAO.delete(id);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByName(username);
    }
}
