package com.soluciones.web.appGrupo4.service.interfaces;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Country;

public interface ICountryService {

    public Response<E_Country> getAllCountries();
}
