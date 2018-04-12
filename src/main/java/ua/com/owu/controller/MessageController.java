package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.editor.PostEditor;
import ua.com.owu.editor.UserEditor;
import ua.com.owu.entity.Comment;
import ua.com.owu.entity.Message;
import ua.com.owu.entity.Post;
import ua.com.owu.entity.User;
import ua.com.owu.service.MailService;
import ua.com.owu.service.MessageServise;
import ua.com.owu.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageServise messageServise;
    @Autowired
    private UserService userService;
    @Autowired
    private UserEditor userEditor;
    public void setUserEditor(UserEditor userEditor) {
        this.userEditor = userEditor;
    }

    @GetMapping("/createMessage")
    public String createMessage(Model model) {
        return "";
    }

    @PostMapping("/saveMessage")
    public String saveMessage(@ModelAttribute("emptyMessage") Message message, BindingResult result, Principal principal) {
        User user = userService.findByName(principal.getName());
        message.setUser(user);
        messageServise.save(message);
        return "redirect:/";
    }

    @GetMapping(value="deleteMessage-{id}")
    public String deleteMessage(@PathVariable int id) {
        messageServise.delete(id);
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(User.class, userEditor);
    }
}
