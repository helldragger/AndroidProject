package com.example.natw.annonce.propriete;

public class PropertyImage {
    private long id = -1;
    private long propertyId = -1;
    private String path = "UNKNOWN PATH";

    public String getPath() {
        return path;
    }

    public PropertyImage setPath(String path) {
        this.path = path;
        return this;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public PropertyImage setPropertyId(long propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public long getId() {
        return id;
    }

    public PropertyImage setId(long id) {
        this.id = id;
        return this;
    }
}
