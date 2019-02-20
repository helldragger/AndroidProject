package com.example.natw.annonce;

import com.example.natw.annonce.propriete.Property;

import java.util.List;

public class ReponseGet {
    public boolean success;
    public List<Property> response;

    public ReponseGet(boolean success, List<Property> response) {
        this.success = success;
        this.response = response;
    }
}
