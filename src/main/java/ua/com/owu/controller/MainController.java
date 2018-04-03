package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.entity.User;
import ua.com.owu.models.Greeting;
import ua.com.owu.models.HelloMessage;
import ua.com.owu.service.*;
import ua.com.owu.valid.UserValidator;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model,Principal principal) {
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("categorise", categoryService.findAll());
        return "index";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("registration")
    public String registration(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email,
                               @RequestParam("file") MultipartFile multipartFile) {
        String realPath = System.getProperty("user.home") + File.separator + "images\\";
        try {
            multipartFile.transferTo(new File(realPath + multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAvatar("\\avatar\\" + multipartFile.getOriginalFilename());
        userService.save(user);
        mailService.send(user);
        return "login";
    }


    @GetMapping("login")
    public String login() {
        return "login";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage helloMessage) {
        return new Greeting(helloMessage.getName());
    }

    UserValidator userValidator;
    public  void bind(WebDataBinder binder){binder.addValidators();}
}