package com.soluciones.web.appGrupo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.entities.E_User;



@Repository
public interface I_user_db extends JpaRepository<E_User, String>{
    

    @Query(nativeQuery = true, value = "SELECT * FROM user LIMIT 2")
    public List<E_User> getUsersLimit();

    public E_User findByEmail(String email);
}
