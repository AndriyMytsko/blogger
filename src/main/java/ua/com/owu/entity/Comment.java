package ua.com.owu.entity;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.owu.service.UserService;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    private Date date = new Date();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Blog blog;


    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(String comment, Blog blog) {
        this.comment = comment;
        this.blog = blog;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        String blog = "";
        if (this.blog != null) blog = blog.toString();
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
