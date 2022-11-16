package com.soluciones.web.appGrupo4.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "activity")
public class E_Activity {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_activity")
    private String idActivity;

    @Column(name = "name_activity")
    private String nameActivity;



    // constructor

    public E_Activity() {
        this.idActivity = "";
        this.nameActivity = "";
    }


    // getters & setters

    public String getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(String idActivity) {
        this.idActivity = idActivity;
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

}
