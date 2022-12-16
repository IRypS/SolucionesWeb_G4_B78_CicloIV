package com.soluciones.web.appGrupo4.service.interfaces;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Genre;

public interface IGenreService {
    
    public Response<E_Genre> getAllGenres();

    public Response<E_Genre> getGenreById(String id);
    
    public boolean genreExists(String name);

    public Response<E_Genre> createGenre(E_Genre genre);

    public Response<E_Genre> deleteGenderById(String id);

}
