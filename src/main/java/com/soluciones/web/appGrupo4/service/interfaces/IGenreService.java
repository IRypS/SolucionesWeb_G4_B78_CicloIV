package com.soluciones.web.appGrupo4.service.interfaces;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Genre;

public interface IGenreService {
    
    public Response<E_Genre> getAllGenres();

    
    // -------------> Temporally Method <-------------
    public E_Genre getGenreById(String id);

}
