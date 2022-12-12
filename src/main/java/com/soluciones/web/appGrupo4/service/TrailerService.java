package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.helper.IPaginationHelper;
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

	@Autowired
	private IPaginationHelper pagination;

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
    public Response<E_Trailer> getPaginatedTrailers(int pageIndexNumber, int elementsPerPage) {
        Response<E_Trailer> response = new Response<>();

		try {
            
            PageRequest pageRequest = PageRequest.of(pageIndexNumber, elementsPerPage);
			Page<E_Trailer> pageData = trailer_entity.findAll(pageRequest);
			Integer totalPages = pageData.getTotalPages();

			if (pageIndexNumber > totalPages ) {
				response.setState(false);
				response.setMessage("Bad Page Request");
				response.setErrorMessage("Este número de paginación no existe");

				return response;
			}

			response.setMessage("Lista de trailers");
			response.setState(true);
			response.setPaginatedData(pageData);
			response.setTotalPagesList(pagination.pageNumberListGenerator(totalPages));
			response.setPagesInformation(pagination.pageInfoGnerator(totalPages, pageIndexNumber));

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
			response.setMessage("No existe el trailer con el ID: " + id);
			response.setErrorMessage(e.getMessage());
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

	
	@Override
	public Response<E_Trailer> getTrailersByTitle(String title) {
		Response<E_Trailer> response = new Response<>();

		try {
            List<E_Trailer> trailers = trailer_entity.findByTitleContainsIgnoreCase(title);

            response.setState(true);
			response.setListData(trailers);
			response.setMessage("Trailers encontrados por Titulo");

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para encontrar los trailers");
			response.setErrorMessage(e.getMessage());
        }

        return response;
	};

	@Override
	public Response<E_Trailer> getTrailersByGenre(String genre) {
		Response<E_Trailer> response = new Response<>();

		try {
            List<E_Trailer> trailers = trailer_entity.getTrailersByGenre(genre);

            response.setState(true);
			response.setListData(trailers);
			response.setMessage("Trailers encontrados por Genero");

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para encontrar los trailers");
			response.setErrorMessage(e.getMessage());
        }

        return response;
	};

	@Override
	public Response<E_Trailer> getTrailersByLanguage(String language, boolean audio) {
		Response<E_Trailer> response = new Response<>();

		try {
            
			if (audio == true) {
				List<E_Trailer> trailers = trailer_entity.getTrailersByAudioLanguage(language);
				response.setState(true);
				response.setListData(trailers);
				response.setMessage("Trailers encontrados por Lenguaje [Audio]");
				return response;

			} else if (audio == false) {
				List<E_Trailer> trailers = trailer_entity.getTrailersBySubtitleLanguage(language);
				response.setState(true);
				response.setListData(trailers);
				response.setMessage("Trailers encontrados por Lenguaje [Subtitulo]");
				return response;
				
			} else {
				response.setState(false);
				response.setMessage("Hubo problemas para encontrar los trailers por lenguaje");
				response.setErrorMessage("Segundo Argumento de la función no enviado o igual a Null");
				return response;
			}

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para encontrar los trailers por lenguaje");
			response.setErrorMessage(e.getMessage());
			return response;
        }

	};


	@Override
	public Response<E_Trailer> getTrailersByIdMovie(String id) {
		Response<E_Trailer> response = new Response<>();

		try {
            List<E_Trailer> trailers = trailer_entity.getTrailersByIdMovie(id);

            response.setState(true);
			response.setListData(trailers);
			response.setMessage("Trailers encontrados por ID de Pelicula");

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para encontrar los trailers");
			response.setErrorMessage(e.getMessage());
        }

        return response;
	};


	@Override
	public Response<E_Trailer> deleteMovieFromTrailer(List<String> idTrailers) {
		
		Response<E_Trailer> response = new Response<>();

		try {

			E_Movie getDefaultMovie = movie_service.getMovieById("0").getData();

			if (idTrailers != null) {
				idTrailers.forEach(id -> {
					E_Trailer target = trailer_entity.findById(id).get();
					target.setMovie(getDefaultMovie);
				});
			}

            response.setState(true);
			response.setMessage("Pelicula eliminada de los trailers");

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para eliminar las peliculas");
			response.setErrorMessage(e.getMessage());
        }

        return response;
	};

	@Override
	public void addView(E_Trailer trailer) {

		try {

			if ( trailer != null ) { trailer.setViews( trailer.getViews() + 1 ); }
			trailer_entity.save(trailer);

		} catch ( Exception e ) {
			e.printStackTrace();
		}
	};
}
