package com.soluciones.web.appGrupo4.repository;

import java.util.Map;

import com.soluciones.web.appGrupo4.model.Language;

public interface ILanguageRepository {
    
    public Map<String, Language> getLanguagesObjects();

}
