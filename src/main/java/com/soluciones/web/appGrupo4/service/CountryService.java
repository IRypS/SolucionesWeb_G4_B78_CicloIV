package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Country;
import com.soluciones.web.appGrupo4.repository.ICountriesRepository;

@Service
public class CountryService implements ICountryService {

    @Autowired
    private ICountriesRepository countryrepo;
    
    @Override
    public List<Country> getAllCountries() {
        return countryrepo.getAllCountryObjects();
    };
}
