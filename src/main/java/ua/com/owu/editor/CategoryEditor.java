package ua.com.owu.editor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.owu.dao.CategoryDAO;
import ua.com.owu.entity.Category;
import ua.com.owu.service.CategoryService;
import java.beans.PropertyEditorSupport;

@Component
public class CategoryEditor extends PropertyEditorSupport {
    @Autowired
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public void setAsText(String fromFormWeakParam) throws IllegalArgumentException {
        int id = Integer.parseInt(fromFormWeakParam);
        Category category = categoryService.findOne(id);
        setValue(category);
    }
}
