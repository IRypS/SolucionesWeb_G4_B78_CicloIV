package com.soluciones.web.appGrupo4.repository;

import java.util.List;
import java.util.Map;

import com.soluciones.web.appGrupo4.model.Category;

public interface ICategoriesRepository {

    public List<Category> getAllCategoriesObjects();

    public Map<String, Category> getTrailersMap();
    
}
