package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.editor.PostEditor;
import ua.com.owu.entity.Post;
import ua.com.owu.entity.Comment;
import ua.com.owu.entity.User;
import ua.com.owu.service.PostService;
import ua.com.owu.service.CategoryService;
import ua.com.owu.service.CommentService;
import ua.com.owu.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostEditor postEditor;

    public void setPostEditor(PostEditor postEditor) {
        this.postEditor = postEditor;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/createComment")
    public String createComment(Model model) {
        return "createCommentPage";
    }

    @PostMapping("/saveComment")
    public String saveComment(@ModelAttribute("emptyComment") Comment comment, Post post, BindingResult result, Principal principal) {
        User user = userService.findByName(principal.getName());
        comment.setUser(user);
        commentService.save(comment);
        return "redirect:/";
    }

    @GetMapping(value="deleteComment-{id}")
    public String deleteComment(@PathVariable int id) {
        commentService.delete(id);
        return "redirect:/";
    }

    @InitBinder("emptyComment")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Post.class, postEditor);
    }
}
