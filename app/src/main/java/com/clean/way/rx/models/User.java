package com.clean.way.rx.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private List<Post> posts;

    public User() {
        this.name = "Yair Carreno";
        this.email = "yair@email";
        this.posts = new ArrayList<>();
    }

    public User(String name, String email, List<Post> posts) {
        this.name = name;
        this.email = email;
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
