package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.com.owu.entity.User;
import ua.com.owu.service.BlogService;
import ua.com.owu.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @GetMapping("delete-{id}")
    public String deleteUser(@PathVariable int some){
//        userService.delete(some);
        return "redirect:/formPage";
    }

    @GetMapping("show-{id}")
    public String showUser(@PathVariable int id, Model model){
        User oneUser = userService.findOne(id);
        model.addAttribute("user", oneUser);
        model.addAttribute("blogs", blogService.findAll());
        return "singlUserPage";
    }

    @GetMapping("userListPage")
    public String userListPage(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "userListPage";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
