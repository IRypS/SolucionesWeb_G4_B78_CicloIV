package com.soluciones.web.appGrupo4.service.interfaces;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;

public interface ITrailerService {
    
    // public List<E_Trailer> getAllTrailers();
    public Response<E_Trailer> getAllTrailers();

    // public E_Trailer getTargetTrailer(String id);
    public Response<E_Trailer> getTrailerById(String id);

    // public List<E_Trailer> getRelatedTrailers(String id);
    public Response<E_Trailer> getRelatedTrailers(String id);

    // public V_Trailer getTrailerById(String id);
    // public Response<E_Trailer> getTrailerById(String id);

    // public String createTrailer(V_Trailer trailer);
    public Response<E_Trailer> createTrailer(E_Trailer trailer, String movieID, String languageID, String subtitleID );

    // public String deleteTrailerById(String id);
    public Response<E_Trailer> deleteTrailerById(String id);
}
