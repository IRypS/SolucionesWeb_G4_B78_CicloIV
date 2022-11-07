package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.repository.I_movie_db;
import com.soluciones.web.appGrupo4.service.interfaces.IMovieService;

public class MovieService implements IMovieService {

    @Autowired
    private I_movie_db movie_entity;

    @Override
    public List<E_Movie> getAllMovies() {
        return movie_entity.findAll();
    };
    
}
