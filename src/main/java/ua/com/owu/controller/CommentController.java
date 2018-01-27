package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.editor.BlogEditor;
import ua.com.owu.entity.Blog;
import ua.com.owu.entity.Comment;
import ua.com.owu.entity.User;
import ua.com.owu.service.BlogService;
import ua.com.owu.service.CommentService;
import ua.com.owu.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogEditor blogEditor;

    public void setBlogEditor(BlogEditor blogEditor) {
        this.blogEditor = blogEditor;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/createComment")
    public String createComment(Model model) {
        model.addAttribute("blogs", blogService.findAll());
        return "createCommentPage";
    }

    @ModelAttribute("emptyComment")
    public Comment comment() {
        return new Comment();
    }

    @PostMapping("/saveComment")
    public String saveComment(@ModelAttribute("emptyComment") Comment comment,Blog blog, BindingResult result, Principal principal) {
        User user = userService.findByName(principal.getName());
        comment.setUser(user);
//        comment.setBlog();
        commentService.save(comment);
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Blog.class, blogEditor);
    }

}
