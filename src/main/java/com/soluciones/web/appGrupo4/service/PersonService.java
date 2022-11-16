package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Response;
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
    public Response<V_Person> getLazyInfoPerson() {

        Response<V_Person> response = new Response<>();

		try {
			response.setMessage("Datos de personas obtenidos correctamente");
			response.setState(true);
			response.setListData( (List<V_Person>)person_modify.findAll() );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener los datos de las personas");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };

    @Override
    public Response<E_Person> getPersonById(String id) {

        Response<E_Person> response = new Response<>();

        try {
            Optional<E_Person> targetPerson = person_entity.findById(id);

            response.setState(true);
			response.setData(targetPerson.get());
			response.setMessage("Persona encontrada: " + targetPerson.get().getName());

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para encontrar a la persona con el ID: " + id);
			response.setErrorMessage(e.getStackTrace().toString());
        }

        return response;
    };
    
}
