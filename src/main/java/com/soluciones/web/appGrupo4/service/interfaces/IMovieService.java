package com.soluciones.web.appGrupo4.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;

public interface IMovieService {

    public Response<E_Movie> getAllMovies();

    public Response<V_Movie> getLazyInfoMovie();

    public Response<E_Movie> getMovieById(String id);

    public Response<E_Movie> createMovie(E_Movie movie, MultipartFile fileM, List<String> idDirectorList, List<String> idGenreList);

    public Response<E_Movie> deleteMovieById(String id);

    public Response<E_Movie> deleteMovieAndTrailers(String id, List<String> idTrailers);
    
}
