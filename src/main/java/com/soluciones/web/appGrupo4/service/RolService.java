package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.entities.E_Rol;
import com.soluciones.web.appGrupo4.service.interfaces.IRolService;


@Service
public class RolService implements IRolService {

    @Override
    public boolean isAdmin(List<E_Rol> roles) {

        if (roles != null) {
            
            for (E_Rol role : roles) {
                if (role.getAuthority().equals("ROLE_ADMIN")) {
                    return true;
                }
            }
        }

        return false;
    }
    
}
