package com.soluciones.web.appGrupo4.service.interfaces;

import java.util.List;

import com.soluciones.web.appGrupo4.model.entities.E_Rol;

public interface IRolService {
    
    public boolean isAdmin(List<E_Rol> roles);

}
