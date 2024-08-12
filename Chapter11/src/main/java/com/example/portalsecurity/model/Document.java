package com.example.portalsecurity.model;

public class Document {
    private Long id;
    private String owner;
    private String content;

    // Constructors, Getters, and Setters
    public Document(Long id, String owner, String content) {
        this.id = id;
        this.owner = owner;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
