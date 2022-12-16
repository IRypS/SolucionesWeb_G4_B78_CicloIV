package com.soluciones.web.appGrupo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soluciones.web.appGrupo4.model.entities.E_Rol;

public interface I_rol_db  extends JpaRepository<E_Rol, String> {
    
    public E_Rol findByAuthority(String authority);

}
