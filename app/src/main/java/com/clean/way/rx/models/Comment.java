package com.clean.way.rx.models;

import java.util.Date;

public class Comment {

    private Date date;
    private String text;

    public Comment() {
        this.date = new Date();
        this.text = "Good practice!";
    }

    public Comment(Date date, String text) {
        this.date = date;
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
