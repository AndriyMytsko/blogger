package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.entity.Category;
import ua.com.owu.entity.Post;

public interface CategoryDAO extends JpaRepository<Category , Integer>{

    @Query("from Category c where c.name =:name")
    public  Category findByCategoryName(@Param("name") String name);

}
