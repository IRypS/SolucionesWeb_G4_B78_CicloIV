package com.soluciones.web.appGrupo4.service.interfaces;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.validators.V_Language;

public interface ILanguageService {

    // public List<E_Language> getAllLanguages();
    public Response<E_Language> getAllTrailers();

    // public List<V_Language> getLazyInfoLanguage();
    public Response<V_Language> getLazyInfoLanguage();

    public Response<E_Language> getLanguageById( String id );

}
