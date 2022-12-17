package com.soluciones.web.appGrupo4.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.helper.IRoleHelper;
import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_User;
import com.soluciones.web.appGrupo4.repository.I_user_db;
import com.soluciones.web.appGrupo4.service.interfaces.IUserService;


@Service
public class UserService implements IUserService {


    @Autowired
    private I_user_db userRepository;

    @Autowired
    private IRoleHelper roleHelper;

    @Autowired
	private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Response<E_User> createUser(E_User user) {

        Response<E_User> response = new Response<>();

        try {

            E_User createUser = new E_User();

            createUser.setUsername( user.getUsername() );
            createUser.setEmail( user.getEmail() );
            createUser.setPassword( passwordEncoder.encode(user.getPassword()) );
            createUser.setAvatarUrl( null );
            createUser.setRoles( Arrays.asList(roleHelper.generateDefaultRole()) );

            userRepository.save(createUser);

            response.setState(true);
            response.setData(createUser);
            response.setMessage("Usuario creado con éxito");
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            response.setState(false);
            response.setMessage("No se pudo crear el usuario");
            response.setErrorMessage(e.getMessage());
            return response;
        }

    };

    @Override
    public Response<E_User> getUserInfo() {
        
        Response<E_User> response = new Response<>();

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = ((UserDetails)principal).getUsername();

            E_User userTarget = userRepository.findByEmail(email);
            response.setState(true);
            response.setData(userTarget);
            response.setMessage("Usuario obtenido con éxito");
            return response;

        } catch (Exception e) {
            response.setState(false);
            response.setMessage("Hubo problemas para obtener la información del usuario");
            response.setErrorMessage(e.getMessage());
            return response;
        }
    };

    @Override
    public boolean userEmailExist(String email) {
        try {
            E_User userInfo = userRepository.findByEmail(email);

            if (userInfo != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    };
    
}
