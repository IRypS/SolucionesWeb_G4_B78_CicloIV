package com.soluciones.web.appGrupo4.service.interfaces;

import java.util.List;

import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;

public interface IMovieService {

    public List<E_Movie> getAllMovies();

    public List<V_Movie> getLazyInfoTrailer();

    public E_Movie getMovieById(String id);

    public String createMovie(E_Movie movie, List<String> idDirectorList, List<String> idGenreList);

    public String deleteTrailerById(String id);
    
}
