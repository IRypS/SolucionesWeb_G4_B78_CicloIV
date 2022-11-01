package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Map;

import com.soluciones.web.appGrupo4.model.Language;

public interface ILanguageService {
    
    public List<Language> getLanguagesListObjects();
    public Map<String, Language> getLanguagesMap();

}
