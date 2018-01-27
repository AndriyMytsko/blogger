package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.entity.Blog;
import ua.com.owu.entity.Comment;
import ua.com.owu.entity.User;
import ua.com.owu.service.BlogService;
import ua.com.owu.service.CommentService;
import ua.com.owu.service.UserService;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/createBlog")
    public String createBlog(Model model) {
        model.addAttribute("emptyBlog", new Blog());
        return "createBlogPage";
    }

    @GetMapping("showBlog-{id}")
    public String showBlog(@PathVariable int id, Model model, Comment comment) {
        Blog blog = blogService.findOne(id);
        model.addAttribute("blogs", blogService.findAll());
        model.addAttribute("comments", commentService.findAll());
        model.addAttribute("blog", blog);
        return "blogPage";
    }

    @GetMapping("deleteBlog-{id}")
    public String deleteBlog(@PathVariable int id) {
       blogService.delete(id);
        return "redirect:/";
    }


    @PostMapping("saveBlog")
    public String saveBlog(@ModelAttribute("emptyBlog") Blog blog, BindingResult result,Principal principal,
                           @RequestParam("file")MultipartFile multipartFile)  {
        User user = userService.findByName(principal.getName());
        System.out.println(blog);
        String realPath = System.getProperty("user.home") + File.separator + "upload\\";
        try {
            multipartFile.transferTo(new File(realPath +multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setAvatar("\\avatar\\" + multipartFile.getOriginalFilename());
        blog.setUser(user);
        blogService.save(blog);
        return "redirect:/";
    }

    @ModelAttribute("emptyComment")
    public Comment comment() {
        return new Comment();
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
}
