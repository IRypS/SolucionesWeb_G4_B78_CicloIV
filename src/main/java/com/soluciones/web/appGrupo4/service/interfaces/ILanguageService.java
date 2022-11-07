package com.soluciones.web.appGrupo4.service.interfaces;

import java.util.List;
import java.util.Map;

import com.soluciones.web.appGrupo4.model.Language;
import com.soluciones.web.appGrupo4.model.entities.E_Language;

public interface ILanguageService {

    public List<E_Language> getAllLanguages();
    
    public List<Language> getLanguagesListObjects();
    public Map<String, Language> getLanguagesMap();

}
