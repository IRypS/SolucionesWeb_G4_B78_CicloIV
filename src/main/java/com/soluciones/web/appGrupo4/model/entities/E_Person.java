package com.soluciones.web.appGrupo4.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "person")
public class E_Person {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_person")
    private String idPerson;

    @Column(name = "name_person")
    private String name;

    @Column(name = "last_name_person")
    private String lastname;

    @Column(name = "birthday_person")
    private String birthday;

    @ManyToMany
    @JoinTable(name = "person_activity",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "activity_id") })
    private List<E_Activity> activitiesList;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private E_Country country;



    // constructor

    public E_Person() {
        this.idPerson = "";
        this.name = "";
        this.lastname = "";
        this.birthday = "";
        this.activitiesList = new ArrayList<E_Activity>();
        this.country = new E_Country();
    }



    // getters & setters

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return birthday;
    }

    public void setAge(String birthday) {
        this.birthday = birthday;
    }

    public List<E_Activity> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(List<E_Activity> activitiesList) {
        this.activitiesList = activitiesList;
    }

    public E_Country getCountry() {
        return country;
    }

    public void setCountry(E_Country country) {
        this.country = country;
    }

}
