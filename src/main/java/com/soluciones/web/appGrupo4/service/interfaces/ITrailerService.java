package com.soluciones.web.appGrupo4.service.interfaces;

import org.springframework.data.domain.Pageable;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;

public interface ITrailerService {

    public Response<E_Trailer> getAllTrailers();

    public Response<E_Trailer> getPaginatedTrailers(Pageable pageable);

    public Response<E_Trailer> getTrailerById(String id);

    public Response<E_Trailer> getRelatedTrailers(String id);

    public Response<E_Trailer> createTrailer(E_Trailer trailer, String movieID, String languageID, String subtitleID );

    public Response<E_Trailer> deleteTrailerById(String id);

}
