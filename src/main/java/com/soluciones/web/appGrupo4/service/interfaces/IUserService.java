package com.soluciones.web.appGrupo4.service.interfaces;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_User;

public interface IUserService {
    
    public Response<E_User> createUser(E_User user);

    public Response<E_User> getUserInfo();

    public boolean userEmailExist(String email);
}
