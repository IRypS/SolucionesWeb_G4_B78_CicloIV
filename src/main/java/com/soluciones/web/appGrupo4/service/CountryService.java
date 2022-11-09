package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.entities.E_Country;
import com.soluciones.web.appGrupo4.repository.I_country_db;
import com.soluciones.web.appGrupo4.service.interfaces.ICountryService;

@Service
public class CountryService implements ICountryService {

    @Autowired
    private I_country_db country_entity;
    
    @Override
    public List<E_Country> getAllCountries() {
        return country_entity.findAll();
    };
}
