package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Map;

import com.soluciones.web.appGrupo4.model.Trailer;

public interface ITrailerService {
    
    public List<Trailer> getAllTrailers();
    
    public Map<String, Trailer> getTrailersMap();

    public List<Trailer> getRelatedTrailers(String id);

}
