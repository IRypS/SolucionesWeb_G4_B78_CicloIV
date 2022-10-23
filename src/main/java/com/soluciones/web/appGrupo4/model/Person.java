package com.soluciones.web.appGrupo4.model;

import java.util.List;

public class Person {
    
    private String id;
    private List<String> activities;
    private String name;
    private String lastName;
    private String age;
    private String country;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<String> getActivities() {
        return activities;
    }
    public void setActivities(List<String> activities) {
        this.activities = activities;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    
}
