package ua.com.owu.service;

import ua.com.owu.entity.Category;
import java.util.List;

public interface CategoryService {

    void save(Category category);

    Category findOne(int id);

    List<Category> findAll();

    Category findByName(String name);

}
