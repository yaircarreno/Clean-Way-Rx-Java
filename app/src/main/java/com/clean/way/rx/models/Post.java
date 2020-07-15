package com.clean.way.rx.models;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private String title;
    private String text;
    private List<Comment> comments;

    public Post() {
        this.title = "Rx";
        this.text = "Clean Way Rx";
        this.comments = new ArrayList<>();
    }

    public Post(String title, String text, List<Comment> comments) {
        this.title = title;
        this.text = text;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
