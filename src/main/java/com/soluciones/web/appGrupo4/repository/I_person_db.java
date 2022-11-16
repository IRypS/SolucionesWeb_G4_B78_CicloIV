package com.soluciones.web.appGrupo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.entities.E_Person;


@Repository
public interface I_person_db extends JpaRepository<E_Person, String> {
    
}
