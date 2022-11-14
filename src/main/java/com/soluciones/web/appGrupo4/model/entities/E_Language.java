package com.soluciones.web.appGrupo4.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "language")
public class E_Language {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_language")
    private String idLanguage;

    @Column(name = "name_language")
    private String nameLanguage;

    @Column(name = "url_icon_language")
    private String iconLanguage;



    // Constructor

    public E_Language() {
        this.idLanguage = "";
        this.nameLanguage = "";
        this.iconLanguage = "";
    }


    
    // Getters & Setters

    public String getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(String idLanguage) {
        this.idLanguage = idLanguage;
    }

    public String getNameLanguage() {
        return nameLanguage;
    }

    public void setNameLanguage(String nameLanguage) {
        this.nameLanguage = nameLanguage;
    }

    public String getIconLanguage() {
        return iconLanguage;
    }

    public void setIconLanguage(String iconLanguage) {
        this.iconLanguage = iconLanguage;
    }

}
