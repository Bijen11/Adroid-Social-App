package com.example.courseapp;

import com.google.android.gms.common.server.converter.StringToIntConverter;

import model.User;

public class Userslist  {
    private String id;
    private String username;
    private String imageURL;

    public Userslist(){


    }

    public Userslist(String id, String username, String imageURL) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
