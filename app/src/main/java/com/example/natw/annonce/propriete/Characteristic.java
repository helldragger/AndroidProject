package com.example.natw.annonce.propriete;

public class Characteristic {
    private String content = "UNKNOWN CHARACTERISTIC";
    private long propertyId = -1;
    private long id = -1;

    public String getContent() {
        return content;
    }

    public Characteristic setContent(String content) {
        this.content = content;
        return this;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public Characteristic setPropertyId(long propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
