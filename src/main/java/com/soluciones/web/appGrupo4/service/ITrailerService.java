package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Map;

import com.soluciones.web.appGrupo4.model.Trailer;

public interface ITrailerService {
    
    List<Trailer> getAllTrailers();
    
    Map<String, Trailer> getTrailersMap();

    List<Trailer> getRelatedTrailers();

}
