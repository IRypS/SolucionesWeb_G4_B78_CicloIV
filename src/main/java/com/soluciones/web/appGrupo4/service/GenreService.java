package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.helper.IVerifySqlError;
import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Genre;
import com.soluciones.web.appGrupo4.repository.I_genre_db;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;

@Service
public class GenreService implements IGenreService {
    
    @Autowired
    private I_genre_db genre_entity;

	@Autowired
    private IVerifySqlError verifySqlError;

    @Override
    public Response<E_Genre> getAllGenres() {
        
        Response<E_Genre> response = new Response<>();

		try {
			response.setMessage("Lista de Generos");
			response.setState(true);
			response.setListData( (List<E_Genre>)genre_entity.findByOrderByNameGenreAsc() );

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Hubo problemas para obtener el listado de generos");
			response.setErrorMessage(e.getStackTrace().toString());
		}

		return response;
    };

    @Override
    public Response<E_Genre> createGenre(E_Genre genre){
        Response<E_Genre> response = new Response<>();

        try {
			E_Genre createGenre = genre_entity.save(genre);
			response.setState(true);
			response.setData(createGenre);
			response.setListData((List<E_Genre>) genre_entity.findAll());
			response.setMessage("Se creó/editó correctamente el genero [" + createGenre.getNameGenre() + "]");

		} catch (Exception e) {
			response.setState(false);
			response.setMessage("Ocurrió un error guardar/actualizar el genero");
			response.setErrorMessage(e.getStackTrace().toString());
		}
        
		return response;
    };

    @Override
    public Response<E_Genre> getGenreById(String id) {
        Response<E_Genre> response = new Response<>();

        try {
            Optional<E_Genre> targetGenre = genre_entity.findById(id);

            response.setState(true);
			response.setData(targetGenre.get());
			response.setMessage("Genero encontrado: " + targetGenre.get().getNameGenre());

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para encontrar el trailer con el ID: " + id);
			response.setErrorMessage(e.getStackTrace().toString());
        }

        return response;
    };

	@Override
	public Response<E_Genre> deleteGenderById(String id) {
		Response<E_Genre> response = new Response<>();

        try {
            Optional<E_Genre> targetGenre = genre_entity.findById(id);

			try {
                genre_entity.deleteById(id);
            } catch (DataAccessException e) {
                response.setState(false);
                response.setMessage("Fallo al eliminar el género");
                response.setData(targetGenre.get());
                response.setErrorMessage( verifySqlError.isConstraintViolation(e) ? 
                    "ERROR SQL-CONSTRAINT-VIOLATION" : e.getMessage());
                return response;
            }

            response.setState(true);
			response.setData(targetGenre.get());
            response.setListData((List<E_Genre>) genre_entity.findAll());
			response.setMessage("Genero eliminado exitósamente: [" + targetGenre.get().getNameGenre() + "]");

        } catch (Exception e) {
            response.setState(false);
			response.setMessage("Hubo problemas para elimar el genero con el ID: " + id);
			response.setErrorMessage(e.getMessage());
        }

        return response;
	}
}
