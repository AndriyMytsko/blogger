package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.owu.entity.User;
import ua.com.owu.service.PostService;
import ua.com.owu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

//    @GetMapping("delete-{id}")
//    public String deleteUser(@PathVariable int id){
//        userService.delete(id);
//        return "userListPage";//"redirect:/formPage";
//    }

    @GetMapping(value="deleteUser-{id}")
    public String deleteUser(@PathVariable int id, Model model) {
        userService.delete(id);
        return "userListPage";//"redirect:/formPage";
    }

    @GetMapping("show-{id}")
    public String showUser(@PathVariable int id, Model model){
        User oneUser = userService.findOne(id);
        model.addAttribute("user", oneUser);
        model.addAttribute("posts", postService.findAll());
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
