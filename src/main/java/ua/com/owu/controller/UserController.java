package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.owu.entity.Category;
import ua.com.owu.entity.Message;
import ua.com.owu.entity.Post;
import ua.com.owu.entity.User;
import ua.com.owu.service.CategoryService;
import ua.com.owu.service.MessageServise;
import ua.com.owu.service.PostService;
import ua.com.owu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private MessageServise messageServise;

    @GetMapping(value="deleteUser-{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/";
    }

    @GetMapping("show-{id}")
    public String showUser(@PathVariable int id, Model model, Category category,Post post,Message message){
        User oneUser = userService.findOne(id);
        model.addAttribute("user", oneUser);
        model.addAttribute("posts", postService.findPostOfCurrentUser(id));
        model.addAttribute("categorise", categoryService.findAll());
        model.addAttribute("message", messageServise.findMessageOfCurrentUser(id));
        return "singlUserPage";
    }

    @GetMapping("userListPage")
    public String userListPage(Model model, Category category) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("categorise", categoryService.findAll());
        return "userListPage";
    }

    @ModelAttribute("emptyPost")
    public Post post() {
        return new Post();
    }

    @ModelAttribute("emptyMessage")
    public Message message() {
        return new Message();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
