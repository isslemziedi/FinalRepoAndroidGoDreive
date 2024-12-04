package com.example.godrivetn.model;

import java.io.Serializable;

public class Owner implements Serializable {
    private String name;
    private String email;
    private String imageUrl;
    private String phoneNumber;

    // Empty constructor for Firebase
    public Owner() {}

    public Owner(String name, String email, String imageUrl, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getImageUrl() { return imageUrl; }
    public String getPhoneNumber() { return phoneNumber; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}