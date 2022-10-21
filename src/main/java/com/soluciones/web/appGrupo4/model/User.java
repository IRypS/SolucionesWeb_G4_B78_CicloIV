package com.soluciones.web.appGrupo4.model;

import java.util.List;

public class User {
    private String id;
    private String role;
    private String email;
    private String password;
    private String username;
    private String avatarUrl;
    private String country;
    private List<String> userLists;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public List<String> getUserLists() {
        return userLists;
    }
    public void setUserLists(List<String> userLists) {
        this.userLists = userLists;
    }
    
}
