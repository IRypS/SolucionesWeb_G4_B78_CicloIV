package com.soluciones.web.appGrupo4.model;

import java.util.List;

// -------------> Temporally class <-------------

public class UserList {
    
    private String id;
    private String userId;
    private String name;
    private List<String> trailersId;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getTrailersId() {
        return trailersId;
    }
    public void setTrailersId(List<String> trailersId) {
        this.trailersId = trailersId;
    }

}
