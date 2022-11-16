package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.UserList;
import com.soluciones.web.appGrupo4.repository.IListRepository;

// -------------> Temporally Service <-------------

@Service
public class ListService implements IListService {

    @Autowired
    private IListRepository listrepo;
    
    @Override
    public List<UserList> getAllLists() {

        return listrepo.getAllUserListObjects();
    };

}
