package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Country;
import com.soluciones.web.appGrupo4.repository.I_country_db;
import com.soluciones.web.appGrupo4.service.interfaces.ICountryService;

@Service
public class CountryService implements ICountryService {

    @Autowired
    private I_country_db country_entity;
    
    @Override
    public Response<E_Country> getAllCountries() {

        Response<E_Country> response = new Response<>();

		try {
			response.setMessage("Lista de Paises");
			response.setState(true);
			response.setListData( (List<E_Country>)country_entity.findAll() );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener el listado de paises");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };
}
