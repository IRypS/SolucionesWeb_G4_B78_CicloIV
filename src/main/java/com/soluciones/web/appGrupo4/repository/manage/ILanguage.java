package com.soluciones.web.appGrupo4.repository.manage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soluciones.web.appGrupo4.model.validators.V_Language;

public interface ILanguage extends JpaRepository<V_Language, String>{
    
}
