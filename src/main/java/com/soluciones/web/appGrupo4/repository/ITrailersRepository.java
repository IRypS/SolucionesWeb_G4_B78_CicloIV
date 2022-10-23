package com.soluciones.web.appGrupo4.repository;

import java.util.List;
import java.util.Map;

import com.soluciones.web.appGrupo4.model.Trailer;

public interface ITrailersRepository {

    List<Trailer> getAllTrailersObjects();

    Map<String, Trailer> getTrailersMap();
    
}
