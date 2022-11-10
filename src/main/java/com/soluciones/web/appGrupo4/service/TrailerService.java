package com.soluciones.web.appGrupo4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.entities.E_Trailer;
import com.soluciones.web.appGrupo4.model.validators.V_Trailer;
import com.soluciones.web.appGrupo4.repository.I_trailer_db;
import com.soluciones.web.appGrupo4.repository.manage.ITrailer;
import com.soluciones.web.appGrupo4.service.interfaces.ITrailerService;

@Service
public class TrailerService implements ITrailerService{

    @Autowired
    private I_trailer_db trailer_entity;
    
    @Autowired
    private ITrailer trailer_modify;

    @Override
    public List<E_Trailer> getAllTrailers() {
        return (List<E_Trailer>)trailer_entity.findAll();
    };

    @Override
    public E_Trailer getTargetTrailer(String id) {
        Optional<E_Trailer> target = Optional.empty();
		target = trailer_entity.findById(id);
		return target.get();
    }

    @Override
    public List<E_Trailer> getRelatedTrailers(String id) {
        return trailer_entity.getLimitedTrailers(id);
    };

    @Override
    public String createTrailer(V_Trailer trailer) {

        V_Trailer createdTrailer = trailer_modify.save(trailer);

        System.out.println(createdTrailer.getId());

        return "Creado correctamente <-------------------------";
    };
}
