package com.soluciones.web.appGrupo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.entities.E_Activity;


@Repository
public interface I_activity_db extends JpaRepository<E_Activity, String> {
    
}
