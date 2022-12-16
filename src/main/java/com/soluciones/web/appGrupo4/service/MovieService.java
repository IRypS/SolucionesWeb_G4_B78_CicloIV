package com.soluciones.web.appGrupo4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.soluciones.web.appGrupo4.helper.IVerifySqlError;
import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.ResponseFile;
import com.soluciones.web.appGrupo4.model.entities.E_Genre;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Person;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;
import com.soluciones.web.appGrupo4.repository.I_movie_db;
import com.soluciones.web.appGrupo4.repository.I_person_db;
import com.soluciones.web.appGrupo4.repository.I_trailer_db;
import com.soluciones.web.appGrupo4.repository.manage.IMovie;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;
import com.soluciones.web.appGrupo4.service.interfaces.IMovieService;
import com.soluciones.web.appGrupo4.utils.interfaces.IImageCloudHandler;
// import com.soluciones.web.appGrupo4.utils.interfaces.IImageLocalHandler;

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

    @Autowired
    private I_trailer_db trailer_entity;

    // @Autowired
    // private IImageLocalHandler imageHandler;

    @Autowired
    private IVerifySqlError verifySqlError;

    @Autowired
    private IImageCloudHandler cloudinaryService;
    

    @Value("${image.folder.path.general}" + "${image.folder.movie.name}")
    private String pathMovie;
    


    @Override
    public Response<E_Movie> getAllMovies() {

        Response<E_Movie> response = new Response<>();

		try {

			response.setMessage("Lista de Peliculas");
			response.setState(true);
            response.setListData(movie_entity.findAll());

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
        ResponseFile responseFile = new ResponseFile();

        try {

            if( !(fileM.isEmpty()) ) {

                try {

                    if (movie.getCoverUrl().equals("")) { movie.setCoverUrl(null);; }
                    if (movie.getCoverUrl() != null) {
                        // imageHandler.deleteImageLocal(movie.getCoverUrl(), pathMovie);
                            String idDelete = movie.getCoverUrl().split("\\.")[0];
                            cloudinaryService.delete(idDelete);
                            movie.setCoverUrl(null);
                    } 

                    // responseFile = imageHandler.saveImageInLocal(fileM, pathMovie);
                    // movie.setCoverUrl(responseFile.getFileName());

                    Map<String, String> result = cloudinaryService.upload(fileM);
                    movie.setCoverUrl(result.get("public_id") + "." + result.get("format"));

                } catch (Exception e) {

                        e.printStackTrace();
                    response.setState(responseFile.getState());
                    response.setMessage("IMG-ERROR | Ocurrió un error al crear el archivo");
                    response.setErrorMessage(responseFile.getErrorMessage());
                    return response;
                }

            }

            List<E_Person> directorsToAdd = this.createDirectorObjectsIntoArray(idDirectorList);
            List<E_Genre> genresToAdd = this.createGenresObjectsIntoArray(idGenreList);
            movie.setDirectorsList(directorsToAdd);
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
    public Response<E_Movie> deleteMovieById(String id) {

        Response<E_Movie> response = new Response<>();

        try {
            Optional<E_Movie> targetMovie = movie_entity.findById(id);

            try {
                movie_entity.deleteById(id);
            } catch (DataAccessException e) {
                response.setState(false);
                response.setMessage("Fallo al eliminar el trailer");
                response.setData(targetMovie.get());
                response.setErrorMessage( verifySqlError.isConstraintViolation(e) ? 
                    "ERROR SQL-CONSTRAINT-VIOLATION" : e.getMessage());
                return response;
            }

            if (targetMovie.get().getCoverUrl().equals("")) { targetMovie.get().setCoverUrl(null); };
            if (targetMovie.get().getCoverUrl() != null) {
                // imageHandler.deleteImageLocal(targetMovie.get().getCoverUrl(), pathMovie);
                try {
                    String idDelete = targetMovie.get().getCoverUrl().split("\\.")[0];
                    cloudinaryService.delete(idDelete);
                } catch (Exception e) {}
            } 

            response.setState(true);
			response.setData(targetMovie.get());
            response.setListData((List<E_Movie>) movie_entity.findAll());
			response.setMessage("Pelicula eliminada exitósamente: [" + targetMovie.get().getName() + "]");

        } catch (Exception e) {

            response.setState(false);
			response.setMessage("Hubo problemas para eliminar el la pelicula con el ID: " + id);
			response.setErrorMessage(e.getMessage().toString());
        }

        return response;
    };


    @Override
    public Response<E_Movie> deleteMovieAndTrailers(String id, List<String> idTrailers) {
        Response<E_Movie> response = new Response<>();

        try {

            if (idTrailers != null) {
                idTrailers.forEach( idTrailer -> {
                    trailer_entity.deleteById(idTrailer);
                } );
            }

            response = this.deleteMovieById(id);

            return response;

        } catch (Exception e) {

            response.setState(false);
			response.setMessage("Hubo problemas para eliminar los trailers de la pelicula con el ID: " + id);
			response.setErrorMessage(e.getMessage().toString());
            return response;
        }

    };


    @Override
    public Response<E_Movie> deleteGenreFromMovies(String id, List<String> idMovies) {

        Response<E_Movie> response = new Response<>();

		try {

			if ( idMovies != null ) {

                idMovies.forEach( movie -> {
                    E_Movie targetMovie = movie_entity.findById(movie).get();
                    List<E_Genre> genresList = new ArrayList<>();

                    targetMovie.getGenreList().forEach( genreObject -> {
                        if (!genreObject.getIdGenre().equals(id)) {
                            genresList.add(genreObject);
                        }
                    } );

                    targetMovie.setGenreList( genresList );
                    movie_entity.save(targetMovie);
                });

            }

            response.setState(true);
			response.setMessage("Género eliminado de las peliculas existósamente");

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para eliminar el género de las peliculas");
			response.setErrorMessage(e.getMessage());

            e.printStackTrace();
        }

        return response;
    };


    @Override
    public List<String> getIdMoviesByGenreId(String id) {
        try {
            return movie_entity.findIdMoviesByGenreId(id);
        } catch (Exception e) {
            return null;
        }
    };

    @Override
    public int getMoviesCountByGenreId(String id) {
        try {
            return movie_entity.findMoviesCountByGenreId(id);
        } catch (Exception e){ 
            return 0; 
        }
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
                Response<E_Genre> genreResponse = genre_service.getGenreById(idGenre);

                if (genreResponse.getState()) {
                    objectGenderList.add(genreResponse.getData());
                }
            } );
        } 

        return objectGenderList;
    };
}
