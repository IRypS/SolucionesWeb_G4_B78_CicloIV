package com.soluciones.web.appGrupo4.model.validators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "language")
public class V_Language {
    
    @Id
    @Column(name = "id_language")
    private String idLanguage;

    @Column(name = "name_language")
    private String nameLanguage;

    @Column(name = "url_icon_language")
    private String iconLanguage;



    // constructor

    public V_Language() {
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
