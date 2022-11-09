package com.soluciones.web.appGrupo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.entities.E_Genre;


@Repository
public interface I_genre_db extends JpaRepository<E_Genre, String> {
    
    List<E_Genre> findByOrderByNameGenreAsc();

}
