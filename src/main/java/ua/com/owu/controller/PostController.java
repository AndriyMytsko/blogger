package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.editor.CategoryEditor;
import ua.com.owu.entity.Category;
import ua.com.owu.entity.Post;
import ua.com.owu.entity.Comment;
import ua.com.owu.entity.User;
import ua.com.owu.service.CategoryService;
import ua.com.owu.service.PostService;
import ua.com.owu.service.CommentService;
import ua.com.owu.service.UserService;

import javax.persistence.Entity;
import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryEditor categoryEditor;

    public void setCategoryEditor(CategoryEditor categoryEditor) {
        this.categoryEditor = categoryEditor;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/createPost")
    public String createPost(Model model, Category category) {
        return "redirect:/";
    }

    @GetMapping("showPost-{id}")
    public String showPost(@PathVariable int id, Model model, Comment comment, Category category) {
        Post post = postService.findOne(id);
        model.addAttribute("comments", commentService.findCommentOfCurrentCategory(id));
        model.addAttribute("post", post);
        return "postPage";
    }

    @GetMapping(value="deletePost-{id}")
    public String deletePost(@PathVariable int id, Model model) {
        Post post = postService.findOne(id);
        postService.delete(id);
        return "redirect:/";
    }

    @PostMapping("savePost")
    public String save(@ModelAttribute("emptyPost") Post post, BindingResult result, Category category, Principal principal,
                       @RequestParam("file") MultipartFile multipartFile) {
        User user = userService.findByName(principal.getName());
        System.out.println(post);
        String realPath = System.getProperty("user.home") + File.separator + "upload\\";
        try {
            multipartFile.transferTo(new File(realPath + multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setAvatar("\\avatar\\" + multipartFile.getOriginalFilename());
        post.setUser(user);
        postService.save(post);
        return "redirect:/";
    }


    @ModelAttribute("emptyComment")
    public Comment comment() {
        return new Comment();
    }


    @InitBinder("emptyPost")
    public void binder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Category.class, categoryEditor);
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }
}
