package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;
import com.soluciones.web.appGrupo4.repository.I_trailer_db;
import com.soluciones.web.appGrupo4.service.interfaces.ILanguageService;
import com.soluciones.web.appGrupo4.service.interfaces.IMovieService;
import com.soluciones.web.appGrupo4.service.interfaces.ITrailerService;

@Service
public class TrailerService implements ITrailerService{

    @Autowired
    private I_trailer_db trailer_entity;

    @Autowired
    private IMovieService movie_service;

    @Autowired
    private ILanguageService language_service;

    @Override
    public Response<E_Trailer> getAllTrailers() {

        Response<E_Trailer> response = new Response<>();

		try {
			response.setMessage("Lista de trailers");
			response.setState(true);
			response.setListData( (List<E_Trailer>)trailer_entity.findAll() );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener el listado de trailers");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };

    @Override
    public Response<E_Trailer> getPaginatedTrailers(Pageable pageable) {
        Response<E_Trailer> response = new Response<>();

		try {
			response.setMessage("Lista de trailers");
			response.setState(true);
			response.setPaginatedData(trailer_entity.findAll(pageable));

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener el listado paginado de trailers");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    }

    @Override
    public Response<E_Trailer> getTrailerById(String id) {

        Response<E_Trailer> response = new Response<>();

        try {
            Optional<E_Trailer> targetTrailer = trailer_entity.findById(id);

            response.setState(true);
			response.setData(targetTrailer.get());
			response.setMessage("Trailer encontrado: " + targetTrailer.get().getTitle());

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para encontrar el trailer con el ID: " + id);
			response.setErrorMessage(e.getStackTrace().toString());
        }

        return response;
    }

    @Override
    public Response<E_Trailer> getRelatedTrailers(String id) {

        Response<E_Trailer> response = new Response<>();

		try {
			response.setMessage("Trailers relacionados");
			response.setState(true);
			response.setListData( (List<E_Trailer>)trailer_entity.getLimitedTrailers(id) );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener el listado de trailers relacionados");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };
    

    @Override
    public Response<E_Trailer> createTrailer(E_Trailer trailer, String movieID, 
                                        String languageID, String subtitleID ) {

        Response<E_Trailer> response = new Response<>();

		try {

            Response<E_Movie> movie = movie_service.getMovieById(movieID);
            if (movie.getState()) { trailer.setMovie(movie.getData()); };

            Response<E_Language> language = language_service.getLanguageById(languageID);
            if (language.getState()) { trailer.setLanguage(language.getData()); };

            Response<E_Language> subtitle = language_service.getLanguageById(subtitleID);
            if (subtitle.getState()) { trailer.setSubtitle(subtitle.getData()); };

			E_Trailer createTrailer = trailer_entity.save(trailer);
			response.setState(true);
			response.setData(createTrailer);
			response.setListData((List<E_Trailer>) trailer_entity.findAll());
			response.setMessage("Se creó/editó correctamente el trailer " + createTrailer.getTitle());

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Ocurrió un error guardar/actualizar el trailer");
			response.setErrorMessage(e.getStackTrace().toString());
		}
        
		return response;
    };

    @Override
    public Response<E_Trailer> deleteTrailerById(String id) {

        Response<E_Trailer> response = new Response<>();

        try {
            Optional<E_Trailer> targetTrailer = trailer_entity.findById(id);
            trailer_entity.deleteById(id);

            response.setState(true);
			response.setData(targetTrailer.get());
            response.setListData((List<E_Trailer>) trailer_entity.findAll());
			response.setMessage("Trailer eliminado exitósamente: [" + targetTrailer.get().getTitle() + "]");

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para elimar el trailer trailer con el ID: " + id);
			response.setErrorMessage(e.getStackTrace().toString());
        }

        return response;
    };

}
