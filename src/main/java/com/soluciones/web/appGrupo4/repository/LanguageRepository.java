package com.soluciones.web.appGrupo4.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.Language;

@Repository
public class LanguageRepository implements ILanguageRepository {
    
    @Override
    public Map<String, Language> getLanguagesObjects() {
        
        Language lang1 = new Language();
        Language lang2 = new Language();
        Language lang3 = new Language();
        Language lang4 = new Language();

        Map<String, Language> languagesMap = new HashMap<String, Language>();

        lang1.setIdLanguage("c66f3e3f-5c0c-40ad-ab40-1f62c04a2f3a");
        lang1.setNameLanguage("Inglés");
        lang1.setIconLanguage("/resources/icons/flags/united-states.svg");

        lang2.setIdLanguage("d695b6b9-9b07-4638-b6ac-7a29b49c9891");
        lang2.setNameLanguage("Latino");
        lang2.setIconLanguage("/resources/icons/flags/mexico.svg");

        lang3.setIdLanguage("c450303a-4777-44d7-80cd-cf7f98c8b86a");
        lang3.setNameLanguage("Castellano");
        lang3.setIconLanguage("/resources/icons/flags/spain.svg");

        lang4.setIdLanguage("be6dc5f5-963b-4bcc-85e7-bdd13fae0095");
        lang4.setNameLanguage("Japonés");
        lang4.setIconLanguage("/resources/icons/flags/japan.svg");


        languagesMap.put(lang1.getIdLanguage(), lang1);
        languagesMap.put(lang2.getIdLanguage(), lang2);
        languagesMap.put(lang3.getIdLanguage(), lang3);
        languagesMap.put(lang4.getIdLanguage(), lang4);

        return languagesMap;     
    };

}
