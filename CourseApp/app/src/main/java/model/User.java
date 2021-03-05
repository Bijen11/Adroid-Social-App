package model;

import com.example.courseapp.Userslist;

public class User{
    private String id;
    private String username;
    private String fullname;
    private String imageURL;

    public User(String id, String username, String fullname) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;

    }

    public User() {
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
