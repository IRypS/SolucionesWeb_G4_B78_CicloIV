package com.soluciones.web.appGrupo4.repository;

import java.util.List;
import java.util.Map;

import com.soluciones.web.appGrupo4.model.Language;

public interface ILanguageRepository {

    public List<Language> getAllLanguagesObjects();
    
    public Map<String, Language> getLanguagesObjects();

}
