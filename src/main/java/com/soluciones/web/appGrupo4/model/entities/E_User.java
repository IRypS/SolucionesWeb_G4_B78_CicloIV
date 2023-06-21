package com.soluciones.web.appGrupo4.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "user")
public class E_User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_user")
    private String id;

    @NotEmpty(message = "El campo no debe estar vacío")
    @Column(name = "username_user")
    private String username;

    @NotEmpty(message = "El campo no debe estar vacío")
    @Email(message = "Ingrese un email válido")
    @Column(name = "email_user")
    private String email;

    @NotEmpty(message = "El campo no debe estar vacío")
    @Column(name = "password_user", columnDefinition="TEXT")
    private String password;

    @Column(name = "avatar_user")
    private String avatarUrl;


    @ManyToMany()
    @JoinTable( name = "user_rol", 
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "rol_id") })
    private List<E_Rol> roles;




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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<E_Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<E_Rol> roles) {
        this.roles = roles;
    }


    
    


}
