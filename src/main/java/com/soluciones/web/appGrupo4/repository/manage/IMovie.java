package com.soluciones.web.appGrupo4.repository.manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.validators.V_Movie;

@Repository
public interface IMovie extends JpaRepository<V_Movie, String> {
    
}
