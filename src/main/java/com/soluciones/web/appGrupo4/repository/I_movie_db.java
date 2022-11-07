package com.soluciones.web.appGrupo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soluciones.web.appGrupo4.model.entities.E_Movie;

public interface I_movie_db extends JpaRepository<E_Movie, String>{
    
}
