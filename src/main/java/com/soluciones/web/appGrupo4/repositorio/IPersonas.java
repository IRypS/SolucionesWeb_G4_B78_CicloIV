package com.soluciones.web.appGrupo4.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.modelo.Personas;


@Repository
public interface IPersonas extends CrudRepository<Personas, Integer> {

}
