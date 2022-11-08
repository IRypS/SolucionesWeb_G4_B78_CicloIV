package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.entities.E_Genre;
import com.soluciones.web.appGrupo4.repository.I_genre_db;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;

@Service
public class GenreService implements IGenreService {
    
    @Autowired
    private I_genre_db genre_entity;

    @Override
    public List<E_Genre> getAllGenres() {
        return genre_entity.findByOrderByNameGenreAsc();
    };
}
