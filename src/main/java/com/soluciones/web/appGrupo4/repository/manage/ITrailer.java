package com.soluciones.web.appGrupo4.repository.manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.validators.V_Trailer;

@Repository
public interface ITrailer extends JpaRepository<V_Trailer, String>{
    
}
