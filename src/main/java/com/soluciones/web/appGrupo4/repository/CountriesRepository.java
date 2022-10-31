package com.soluciones.web.appGrupo4.repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.Country;

@Repository
public class CountriesRepository implements ICountriesRepository {

    @Override
    public List<Country> getAllCountryObjects() {

        Country country1 = new Country();
        Country country2 = new Country();
        Country country3 = new Country();
        Country country4 = new Country();
        Country country5 = new Country();
        
        country1.setId(UUID.randomUUID().toString());
        country1.setCountryname("China");
        country1.setCountryIcon("/resources/icons/flags/china.svg");

        country2.setId(UUID.randomUUID().toString());
        country2.setCountryname("Japan");
        country2.setCountryIcon("/resources/icons/flags/japan.svg");

        country3.setId(UUID.randomUUID().toString());
        country3.setCountryname("Peru");
        country3.setCountryIcon("/resources/icons/flags/peru.svg");

        country4.setId(UUID.randomUUID().toString());
        country4.setCountryname("Spain");
        country4.setCountryIcon("/resources/icons/flags/spain.svg");

        country5.setId(UUID.randomUUID().toString());
        country5.setCountryname("USA");
        country5.setCountryIcon("/resources/icons/flags/united-states.svg");

        List<Country> countryList = Arrays.asList(country1, country2, country3, country4, country5);

        return countryList;
    };
    
}
