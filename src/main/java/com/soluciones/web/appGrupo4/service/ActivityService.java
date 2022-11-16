package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.entities.E_Activity;
import com.soluciones.web.appGrupo4.repository.I_activity_db;
import com.soluciones.web.appGrupo4.service.interfaces.IActivityService;

@Service
public class ActivityService implements IActivityService {

    @Autowired
    private I_activity_db activity_entity;
 
    @Override
    public List<E_Activity> getAllActivities() {
        return activity_entity.findAll();
    };
    
}
