package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Genre;
import com.soluciones.web.appGrupo4.repository.I_genre_db;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;

@Service
public class GenreService implements IGenreService {
    
    @Autowired
    private I_genre_db genre_entity;

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
    public E_Genre getGenreById(String id) {
        return genre_entity.findById(id).get();
    };
}
