package com.example.natw.annonce.propriete;

public class Comment {

    private long id = -1;
    private long author_id = -1;
    private long propertyId = -1;
    private String content = "EMPTY COMMENT";

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public long getId() {
        return id;
    }

    public Comment setId(long id) {
        this.id = id;
        return this;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public Comment setPropertyId(long propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public long getAuthor() {
        return author_id;
    }

    public Comment setAuthor(long author_id) {
        this.author_id = author_id;
        return this;
    }
}
