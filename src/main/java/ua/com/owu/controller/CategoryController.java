package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.entity.Category;
import ua.com.owu.entity.Comment;
import ua.com.owu.entity.Post;
import ua.com.owu.service.CategoryService;
import ua.com.owu.service.CommentService;
import ua.com.owu.service.PostService;
import ua.com.owu.service.UserService;

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
    public String createCategory(Model model) {
        model.addAttribute("emptyCategory", new Category());
        return "createCategoryPage";
    }

    @GetMapping("showCategory-{id}")
    public String showCategory(@PathVariable int id, Model model, Post post) {
        Category category = categoryService.findOne(id);
        model.addAttribute("posts", postService.findPostOfCurrentCategory(id));
        model.addAttribute("category", category);
        return "categoryPage";
    }

    @PostMapping("/saveCategory")
    public String save(@ModelAttribute("emptyCategory") Category category) {
        categoryService.save(category);
        return "redirect:/";
    }

    @ModelAttribute("emptyPost")
    public Post post() {
        return new Post();
    }

}