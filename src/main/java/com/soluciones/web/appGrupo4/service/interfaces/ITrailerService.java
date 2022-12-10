package com.soluciones.web.appGrupo4.service.interfaces;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;

public interface ITrailerService {

    public Response<E_Trailer> getAllTrailers();

    public Response<E_Trailer> getPaginatedTrailers(int pageIndexNumber, int elementsPerPage);

    public Response<E_Trailer> getTrailerById(String id);

    public Response<E_Trailer> getRelatedTrailers(String id);

    public Response<E_Trailer> createTrailer(E_Trailer trailer, String movieID, String languageID, String subtitleID );

    public Response<E_Trailer> deleteTrailerById(String id);

    public Response<E_Trailer> getTrailersByTitle(String title);

    public Response<E_Trailer> getTrailersByGenre(String genre);

    public Response<E_Trailer> getTrailersByLanguage(String language, boolean audio);

}
