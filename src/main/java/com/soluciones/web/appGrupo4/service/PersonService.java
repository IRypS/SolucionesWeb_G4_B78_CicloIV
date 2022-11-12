package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.entities.E_Person;
import com.soluciones.web.appGrupo4.model.validators.V_Person;
import com.soluciones.web.appGrupo4.repository.I_person_db;
import com.soluciones.web.appGrupo4.repository.manage.IPerson;
import com.soluciones.web.appGrupo4.service.interfaces.IPersonService;


@Service
public class PersonService implements IPersonService {

    @Autowired
    private I_person_db person_entity;

    @Autowired
    private IPerson person_modify;

    @Override
    public List<E_Person> getAllPeople() {
        return person_entity.findAll();
    };

    @Override
    public List<V_Person> getLazyInfoPerson() {
        return person_modify.findAll();
    };

    @Override
    public E_Person getPersonById(String id) {
        return person_entity.findById(id).get();
    };
    
}
