package com.soluciones.web.appGrupo4.service.interfaces;

import java.util.List;

import com.soluciones.web.appGrupo4.model.entities.E_Person;
import com.soluciones.web.appGrupo4.model.validators.V_Person;

public interface IPersonService {
    
    public List<E_Person> getAllPeople();

    public List<V_Person> getLazyInfoPerson();

    public E_Person getPersonById(String id);

}
