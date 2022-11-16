package com.soluciones.web.appGrupo4.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// -------------> Temporally class <-------------

public class User {

    private String id;
    private String role;

    @NotEmpty(message = "El campo no debe estar vacío")
    @Email(message = "Ingrese un email válido")
    private String email;

    @NotEmpty(message = "El campo no debe estar vacío")
    @Size(min = 8, max = 30, message = "Ingrese más de 8 caracteres y menos de 30")
    private String password;

    @NotEmpty(message = "El campo no debe estar vacío")
    private String confirmPassword;

    @NotEmpty(message = "El nombre de usuario no debe estar vacio")
    @Size(min = 3, max = 40, message = "Ingrese más de 3 caracteres y menos de 40")
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
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
