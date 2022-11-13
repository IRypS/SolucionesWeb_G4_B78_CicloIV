package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.validators.V_Language;
import com.soluciones.web.appGrupo4.repository.I_language_db;
import com.soluciones.web.appGrupo4.repository.manage.ILanguage;
import com.soluciones.web.appGrupo4.service.interfaces.ILanguageService;

@Service
public class LanguageService implements ILanguageService {

    @Autowired
    private I_language_db language_entity;

    @Autowired
    private ILanguage language_modify;

    @Override
    public Response<E_Language> getAllTrailers() {

        Response<E_Language> response = new Response<>();

		try {
			response.setMessage("Lista de Lenguajes");
			response.setState(true);
			response.setListData( (List<E_Language>)language_entity.findByOrderByNameLanguageAsc() );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener el listado de lenguajes");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };

    @Override
    public List<V_Language> getLazyInfoLanguage() {
        return language_modify.findAll();
    };
    
}
