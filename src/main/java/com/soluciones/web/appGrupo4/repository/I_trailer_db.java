package com.soluciones.web.appGrupo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.entities.E_Trailer;


@Repository
public interface I_trailer_db extends JpaRepository<E_Trailer, String>{
    
    @Query(nativeQuery = true, value = "SELECT * FROM trailer WHERE id_trailer NOT LIKE (?1) ORDER BY rand() LIMIT 5")
    List<E_Trailer> getLimitedTrailers(String id);

}
