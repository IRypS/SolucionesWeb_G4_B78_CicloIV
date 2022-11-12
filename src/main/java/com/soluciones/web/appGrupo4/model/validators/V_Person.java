package com.soluciones.web.appGrupo4.model.validators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "person")
public class V_Person {
    
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

    @Column(name = "country_id")
    private String countryId;




    public V_Person() {
        this.idPerson = "";
        this.name = "";
        this.lastname = "";
        this.birthday = "0000-00-00";
        this.countryId = "0";
    }




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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

}
