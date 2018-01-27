package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.entity.Comment;
import ua.com.owu.service.CommentService;
import ua.com.owu.service.UserService;

import java.util.List;

@RestController
public class MyRESTController {
    @Autowired
    CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/saveComment")
    public Comment saveCommentREST(@RequestBody Comment comment){
//        User user = userService.findByName(principal.getName());
//        post.setUser(user);
        commentService.save(comment);
        return comment;
    }

    @GetMapping("/show")
    public List<Comment> show(){
        return commentService.findAll();
    }

}
