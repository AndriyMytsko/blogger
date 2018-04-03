package ua.com.owu.editor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.owu.entity.Post;
import ua.com.owu.service.PostService;

import java.beans.PropertyEditorSupport;
@Component
public class PostEditor extends PropertyEditorSupport {
    @Autowired
    private PostService postService;

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void setAsText(String fromFormWeakParam) throws IllegalArgumentException {
        System.out.println(fromFormWeakParam + "!");
        int id = Integer.parseInt(fromFormWeakParam);
        Post post = postService.findOne(id);
        setValue(post);
    }
}
