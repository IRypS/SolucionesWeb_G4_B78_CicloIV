package com.soluciones.web.appGrupo4.service.interfaces;

import java.util.List;

import com.soluciones.web.appGrupo4.model.entities.E_Trailer;
import com.soluciones.web.appGrupo4.model.validators.V_Trailer;

public interface ITrailerService {
    
    public List<E_Trailer> getAllTrailers();

    public E_Trailer getTargetTrailer(String id);

    public List<E_Trailer> getRelatedTrailers(String id);

    public String createTrailer(V_Trailer trailer);

}
