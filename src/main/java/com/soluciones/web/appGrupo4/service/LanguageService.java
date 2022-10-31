package com.soluciones.web.appGrupo4.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Language;
import com.soluciones.web.appGrupo4.repository.ILanguageRepository;

@Service
public class LanguageService implements ILanguageService {

    @Autowired
    private ILanguageRepository languageRepo;

    @Override
    public Map<String, Language> getLanguagesMap() {

        return languageRepo.getLanguagesObjects();

    };
    
}
