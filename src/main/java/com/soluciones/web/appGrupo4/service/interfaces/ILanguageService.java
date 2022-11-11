package com.soluciones.web.appGrupo4.service.interfaces;

import java.util.List;
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.validators.V_Language;

public interface ILanguageService {

    public List<E_Language> getAllLanguages();

    public List<V_Language> getLazyInfoLanguage();

}
