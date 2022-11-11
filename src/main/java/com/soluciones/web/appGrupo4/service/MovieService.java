package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;
import com.soluciones.web.appGrupo4.repository.I_movie_db;
import com.soluciones.web.appGrupo4.repository.manage.IMovie;
import com.soluciones.web.appGrupo4.service.interfaces.IMovieService;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private I_movie_db movie_entity;

    @Autowired
    private IMovie movie_modify;

    @Override
    public List<E_Movie> getAllMovies() {
        return movie_entity.findAll();
    };

    @Override
    public List<V_Movie> getLazyInfoTrailer() {
        return movie_modify.findAll();
    };
    
    @Override
    public String createTrailer(V_Movie movie) {
        V_Movie createdTrailer = movie_modify.save(movie);

        System.out.println(createdTrailer.getIdMovie());

        return "Creado correctamente <-------------------------";
    };
}
