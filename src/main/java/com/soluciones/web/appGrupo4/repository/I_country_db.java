package com.soluciones.web.appGrupo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soluciones.web.appGrupo4.model.entities.E_Country;

public interface I_country_db extends JpaRepository<E_Country, String> {
    
}
