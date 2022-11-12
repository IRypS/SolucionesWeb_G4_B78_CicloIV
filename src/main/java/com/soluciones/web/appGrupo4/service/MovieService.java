package com.soluciones.web.appGrupo4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Person;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;
import com.soluciones.web.appGrupo4.repository.I_movie_db;
import com.soluciones.web.appGrupo4.repository.manage.IMovie;
import com.soluciones.web.appGrupo4.service.interfaces.IMovieService;
import com.soluciones.web.appGrupo4.service.interfaces.IPersonService;

@Service
public class MovieService implements IMovieService {

    
    @Autowired
    private I_movie_db movie_entity;
    
    @Autowired
    private IMovie movie_modify;

    @Autowired
    private IPersonService person_service;
    

    @Override
    public List<E_Movie> getAllMovies() {
        return movie_entity.findAll();
    };

    @Override
    public List<V_Movie> getLazyInfoTrailer() {
        return movie_modify.findAll();
    };
    
    @Override
    public String createMovie(E_Movie movie, List<String> idDirectorList) {

        movie.setDirectorsList(this.createDirectorsIntoArray(idDirectorList));

        movie_entity.save(movie);
        System.out.println("Creado correctamente <-------------------------");
        return "Creado correctamente <-------------------------";
    };




    
    public List<E_Person> createDirectorsIntoArray(List<String> idDirectorList) {

        List<E_Person> directorList = new ArrayList<>();

        if(idDirectorList != null) {
            idDirectorList.forEach( (idDirector) -> {
                E_Person person = person_service.getPersonById(idDirector);
                directorList.add(person);
            } );
        } 

        return directorList;
    };
}
