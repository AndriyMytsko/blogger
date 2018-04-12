package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.entity.Category;
import ua.com.owu.entity.Comment;
import ua.com.owu.entity.Post;
import ua.com.owu.service.CategoryService;
import ua.com.owu.service.CommentService;
import ua.com.owu.service.PostService;
import ua.com.owu.service.UserService;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/createCategory")
    public String createCategory(Model model, Category category) {
        model.addAttribute("emptyCategory", new Category());
        model.addAttribute("categorise", categoryService.findAll());
        return "createCategoryPage";
    }

    @GetMapping("showCategory-{id}")
    public String showCategory(@PathVariable int id, Model model, Post post) {
        Category category = categoryService.findOne(id);
        model.addAttribute("posts", postService.findPostOfCurrentCategory(id));
        model.addAttribute("categorise", categoryService.findAll());
        model.addAttribute("category", category);
        return "categoryPage";
    }

    @PostMapping("/saveCategory")
    public String save(@ModelAttribute("emptyCategory")Category category,
                        @RequestParam("file") MultipartFile multipartFile) {
        String realPath = System.getProperty("user.home") + File.separator + "images\\";
        try {
            multipartFile.transferTo(new File(realPath + multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        category.setAvatar("\\avatar\\" + multipartFile.getOriginalFilename());
        categoryService.save(category);
        return "redirect:/";
    }

    @GetMapping(value="deleteCategory-{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
        return "redirect:/";
    }

    @ModelAttribute("emptyPost")
    public Post post() {
        return new Post();
    }

}