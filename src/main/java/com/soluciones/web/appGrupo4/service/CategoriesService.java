package com.soluciones.web.appGrupo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Category;
import com.soluciones.web.appGrupo4.repository.ICategoriesRepository;

@Service
public class CategoriesService implements ICategoriesService {

    @Autowired
    private ICategoriesRepository categoryRepo;    

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.getAllCategoriesObjects();
    };
}
