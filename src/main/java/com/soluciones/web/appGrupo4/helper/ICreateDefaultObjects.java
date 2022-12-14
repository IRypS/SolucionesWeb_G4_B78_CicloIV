package com.soluciones.web.appGrupo4.helper;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Person;

public interface ICreateDefaultObjects {
    
    Response<E_Language> createDefaultLanguages();

    Response<E_Person> createDefaultPersons();

    Response<E_Movie> createDefaultMovies();
    
}
