package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.entity.User;
import ua.com.owu.models.Greeting;
import ua.com.owu.models.HelloMessage;
import ua.com.owu.service.BlogService;
import ua.com.owu.service.MailService;
import ua.com.owu.service.CommentService;
import ua.com.owu.service.UserService;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model,Principal principal) {
        model.addAttribute("blogs", blogService.findAll());
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

}