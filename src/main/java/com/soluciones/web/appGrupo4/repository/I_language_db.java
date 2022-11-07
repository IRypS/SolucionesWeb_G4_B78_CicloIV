package com.soluciones.web.appGrupo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soluciones.web.appGrupo4.model.entities.E_Language;

public interface I_language_db extends JpaRepository<E_Language, String> {
    
    List<E_Language> findByOrderByNameLanguageAsc();
}
