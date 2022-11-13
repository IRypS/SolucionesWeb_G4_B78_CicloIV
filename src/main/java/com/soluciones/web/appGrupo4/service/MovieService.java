package com.soluciones.web.appGrupo4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Genre;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Person;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;
import com.soluciones.web.appGrupo4.repository.I_movie_db;
import com.soluciones.web.appGrupo4.repository.manage.IMovie;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;
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

    @Autowired
    private IGenreService genre_service;
    


    @Override
    public Response<E_Movie> getAllMovies() {

        Response<E_Movie> response = new Response<>();

		try {
			response.setMessage("Lista de Peliculas");
			response.setState(true);
			response.setListData( (List<E_Movie>)movie_entity.findAll() );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener el listado de peliculas");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };

    @Override
    public Response<V_Movie> getLazyInfoMovie() {

        Response<V_Movie> response = new Response<>();

		try {
			response.setMessage("Datos peliculas obtenidos correctamente");
			response.setState(true);
			response.setListData( (List<V_Movie>)movie_modify.findAll() );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener los datos de las peliculas");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };

    @Override
    public Response<E_Movie> getMovieById(String id) {

        Response<E_Movie> response = new Response<>();

        try {
            Optional<E_Movie> targetMovie = movie_entity.findById(id);

            response.setState(true);
			response.setData(targetMovie.get());
			response.setMessage("Pelicula encontrada: " + targetMovie.get().getName());

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para encontrar la pelicula con el ID: " + id);
			response.setErrorMessage(e.getStackTrace().toString());
        }

        return response;

    };
    
    @Override
    public String createMovie(E_Movie movie, List<String> idDirectorList, List<String> idGenreList) {

        List<E_Person> directorsToAdd = this.createDirectorObjectsIntoArray(idDirectorList);
        movie.setDirectorsList(directorsToAdd);

        List<E_Genre> genresToAdd = this.createGenresObjectsIntoArray(idGenreList);
        movie.setGenreList(genresToAdd);

        movie_entity.save(movie);
        System.out.println("Creado correctamente <-------------------------");
        return "Creado correctamente <-------------------------";
    };


    @Override
    public String deleteTrailerById(String id) {
        movie_entity.deleteById(id);
        return "";
    };


    public List<E_Person> createDirectorObjectsIntoArray(List<String> idDirectorList) {

        List<E_Person> objectPersonList = new ArrayList<>();

        if(idDirectorList != null) {
            idDirectorList.forEach( (idDirector) -> {
                E_Person person = person_service.getPersonById(idDirector);
                objectPersonList.add(person);
            } );
        } 

        return objectPersonList;
    };

    public List<E_Genre> createGenresObjectsIntoArray(List<String> idGenreList) {

        List<E_Genre> objectGenderList = new ArrayList<>();

        if(idGenreList != null) {
            idGenreList.forEach( (idGenre) -> {
                E_Genre genre = genre_service.getGenreById(idGenre);
                objectGenderList.add(genre);
            } );
        } 

        return objectGenderList;
    };
}
