package com.soluciones.web.appGrupo4.service.interfaces;

import java.util.List;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Person;
import com.soluciones.web.appGrupo4.model.validators.V_Person;

public interface IPersonService {
    
    // -------------> Temporally Method <-------------
    public List<E_Person> getAllPeople();


    
    public Response<V_Person> getLazyInfoPerson();

    public Response<E_Person> getPersonById(String id);

}
