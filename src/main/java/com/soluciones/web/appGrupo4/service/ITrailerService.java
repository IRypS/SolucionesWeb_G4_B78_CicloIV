package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Map;

import com.soluciones.web.appGrupo4.model.Trailer;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;

public interface ITrailerService {
    
    public List<E_Trailer> getAllTrailers();
    
    public Map<String, Trailer> getTrailersMap();

    public List<Trailer> getRelatedTrailers(String id);

}
