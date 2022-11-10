package com.soluciones.web.appGrupo4.repository.manage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soluciones.web.appGrupo4.model.validators.V_Trailer;

public interface ITrailer extends JpaRepository<V_Trailer, String>{
    
}
