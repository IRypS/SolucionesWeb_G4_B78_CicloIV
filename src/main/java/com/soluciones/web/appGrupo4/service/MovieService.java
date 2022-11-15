package com.soluciones.web.appGrupo4.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Genre;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Person;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;
import com.soluciones.web.appGrupo4.repository.I_movie_db;
import com.soluciones.web.appGrupo4.repository.I_person_db;
import com.soluciones.web.appGrupo4.repository.manage.IMovie;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;
import com.soluciones.web.appGrupo4.service.interfaces.IMovieService;

@Service
public class MovieService implements IMovieService {

    
    @Autowired
    private I_movie_db movie_entity;
    
    @Autowired
    private IMovie movie_modify;

    @Autowired
    private I_person_db person_entity;

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
    public Response<E_Movie> createMovie(E_Movie movie, MultipartFile fileM, List<String> idDirectorList, List<String> idGenreList) {

        Response<E_Movie> response = new Response<>();

        try {
            
            System.out.println("RESULTADOS DEL SERVICE");
            System.out.println(movie.getIdMovie());
            System.out.println(movie.getName());
            System.out.println(movie.getDuration());
            System.out.println(movie.getSynopsis());
            System.out.println(movie.getCoverUrl());
            System.out.println(movie.getDirectorsList().size());
            System.out.println(movie.getGenreList().size());

            if( (fileM.isEmpty()) ) {
                System.out.println("NO SE ENVIÓ NADA");
            }
            if( !(fileM.isEmpty()) ) {
                System.out.println("TENEMOS DATA !!!!");

                try {
                    if (movie.getCoverUrl().equals("")) { movie.setCoverUrl(null); };

                    if (movie.getCoverUrl() != null) {
                        Path pathImage = Paths.get("src//main//resources//static//resources//upload//movie//cover");
                        Path pathDeleteFile = Paths.get(pathImage + "//" + movie.getCoverUrl());
                        File fileToDelete = pathDeleteFile.toFile();
                        if (fileToDelete.exists()) {
							fileToDelete.delete();
						}
                    } 

                    String newNameFile = UUID.randomUUID().toString();
					String extesionFile = org.springframework.util.StringUtils.getFilenameExtension(fileM.getOriginalFilename());

                    byte[] bytesCoverImg = fileM.getBytes();
                    Path pathImage = Paths.get("src//main//resources//static//resources//upload//movie//cover");
                    Path completePath = Paths.get(pathImage + "//" + newNameFile + "." + extesionFile);
                    Files.write(completePath, bytesCoverImg);
                    movie.setCoverUrl(newNameFile + "." + extesionFile);

                } catch (Exception e) {
                    response.setState(false);
					response.setMessage("IMG-ERROR");
					response.setErrorMessage(e.getStackTrace().toString());
					return response;
                }
            }



            List<E_Person> directorsToAdd = this.createDirectorObjectsIntoArray(idDirectorList);
            movie.setDirectorsList(directorsToAdd);

            List<E_Genre> genresToAdd = this.createGenresObjectsIntoArray(idGenreList);
            movie.setGenreList(genresToAdd);

            E_Movie createMovie = movie_entity.save(movie);

			response.setState(true);
			response.setData(createMovie);
			response.setListData((List<E_Movie>) movie_entity.findAll());
			response.setMessage("Se creó/editó correctamente la pelicula " + createMovie.getName());
        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Ocurrió un error al guardar/actualizar la pelicula");
			response.setErrorMessage(e.getStackTrace().toString());
        }

        return response;
    };


    @Override
    public Response<E_Movie> deleteTrailerById(String id) {

        Response<E_Movie> response = new Response<>();

        try {
            Optional<E_Movie> targetMovie = movie_entity.findById(id);

            if (targetMovie.get().getCoverUrl().equals("")) { targetMovie.get().setCoverUrl(null); };

            if (targetMovie.get().getCoverUrl() != null) {
                Path pathImage = Paths.get("src//main//resources//static//resources//upload//movie//cover");
                Path pathDeleteFile = Paths.get(pathImage + "//" + targetMovie.get().getCoverUrl());
                File fileToDelete = pathDeleteFile.toFile();
                if (fileToDelete.exists()) {
                    fileToDelete.delete();
                } 
            } 

            movie_entity.deleteById(id);

            response.setState(true);
			response.setData(targetMovie.get());
            response.setListData((List<E_Movie>) movie_entity.findAll());
			response.setMessage("Pelicula eliminada exitósamente: [" + targetMovie.get().getName() + "]");

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para elimar el la pelicula con el ID: " + id);
			response.setErrorMessage(e.getMessage().toString());
        }

        return response;
    };


    public List<E_Person> createDirectorObjectsIntoArray(List<String> idDirectorList) {

        List<E_Person> objectPersonList = new ArrayList<>();

        if(idDirectorList != null) {
            idDirectorList.forEach( (idDirector) -> {
                E_Person person = person_entity.findById(idDirector).get();
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
