package com.example.natw.annonce;

import android.content.Intent;

import com.example.natw.annonce.propriete.Property;

public class Config {
    public static final String USER = "user";
    public static final String PROPERTY = "user";

    public static Property getCurrentProperty(Intent intent) {
        Property annonce = new Property();
        if (intent.getSerializableExtra(PROPERTY) != null) {
            annonce = (Property) intent.getSerializableExtra(PROPERTY);
        }
        return annonce;
    }

    public static long getCurrentUser(Intent intent) {
        long user = -1;
        if (intent.getSerializableExtra(USER) != null) {
            user = (long) intent.getSerializableExtra(USER);
        }
        return user;
    }

    public static void setCurrentUser(Intent intent, long id) {
        intent.putExtra(USER, id);
    }

    public static void setCurrentProperty(Intent intent, Property property) {
        intent.putExtra(PROPERTY, property);
    }
}
