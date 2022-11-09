package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soluciones.web.appGrupo4.model.entities.E_Person;
import com.soluciones.web.appGrupo4.repository.I_person_db;
import com.soluciones.web.appGrupo4.service.interfaces.IPersonService;

public class PersonService implements IPersonService {

    @Autowired
    private I_person_db person_entity;

    @Override
    public List<E_Person> getAllPeople() {
        return person_entity.findAll();
    };
    
}
