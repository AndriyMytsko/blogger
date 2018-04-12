package ua.com.owu.editor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.owu.entity.Category;
import ua.com.owu.entity.User;
import ua.com.owu.service.CategoryService;
import ua.com.owu.service.UserService;

import java.beans.PropertyEditorSupport;

@Component
public class UserEditor extends PropertyEditorSupport {
    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void setAsText(String fromFormWeakParam) throws IllegalArgumentException {
        int id = Integer.parseInt(fromFormWeakParam);
        User user = userService.findOne(id);
        setValue(user);
    }
}
