package com.soluciones.web.appGrupo4.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.entities.E_Rol;
import com.soluciones.web.appGrupo4.model.entities.E_User;
import com.soluciones.web.appGrupo4.repository.I_rol_db;
import com.soluciones.web.appGrupo4.repository.I_user_db;



@Service
public class RoleHelper implements IRoleHelper {

    @Autowired
    private I_user_db userRepository;

    @Autowired
    private I_rol_db roleRepository;
    
    @Override
    public E_Rol generateDefaultRole() {
        
        List<E_User> userList = userRepository.getUsersLimit();

        if (userList.size() > 0) {
            E_Rol commonRol = roleRepository.findByAuthority("ROLE_USER");
            
            if (commonRol != null) {
                return commonRol;

            } else {
                E_Rol newUserRol = new E_Rol();
                newUserRol.setAuthority("ROLE_USER");
                E_Rol savedRol = roleRepository.save(newUserRol);
                return savedRol;
            }

        } else {

            E_Rol adminRol = roleRepository.findByAuthority("ROLE_ADMIN");
            
            if (adminRol != null) {
                return adminRol;

            } else {
                E_Rol newAdminRole = new E_Rol();
                newAdminRole.setAuthority("ROLE_ADMIN");
                E_Rol savedRol = roleRepository.save(newAdminRole);
                return savedRol;
            }
        }
    }
}
