package com.mechi.model;

import java.util.Date;

public class Comment {
    private Long id;
    private String message;
    private Date created;
    private String author;

    public Comment() {

    }

    public Comment(Long id, String message, Date created, String author) {
        this.id = id;
        this.message = message;
        this.created = created;
        this.author = author;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
