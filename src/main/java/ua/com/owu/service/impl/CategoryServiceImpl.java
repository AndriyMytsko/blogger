package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.CategoryDAO;
import ua.com.owu.entity.Category;
import ua.com.owu.service.CategoryService;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public void save(Category category) {
    categoryDAO.save(category);
    }

    public Category findOne(int id) {
        return categoryDAO.findOne(id);
    }

    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findByName(String name) {
        return categoryDAO.findByCategoryName(name);
    }

    @Override
    public void delete(int id) {
        categoryDAO.delete(id);
    }

}
