package com.soluciones.web.appGrupo4.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.Category;

@Repository
public class CategoriesRepository implements ICategoriesRepository {

    @Override
    public List<Category> getAllCategoriesObjects() {

        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();
        Category category4 = new Category();
        Category category5 = new Category();
        Category category6 = new Category();
        Category category7 = new Category();

        category1.setId(UUID.randomUUID().toString());
        category1.setName("Accion");

        category2.setId(UUID.randomUUID().toString());
        category2.setName("Adventure");

        category3.setId(UUID.randomUUID().toString());
        category3.setName("Animation");

        category4.setId(UUID.randomUUID().toString());
        category4.setName("Comedy");

        category5.setId(UUID.randomUUID().toString());
        category5.setName("Drama");

        category6.setId(UUID.randomUUID().toString());
        category6.setName("Romance");

        category7.setId(UUID.randomUUID().toString());
        category7.setName("Thriller");


        List<Category> categoryList = Arrays.asList( category1, category2, category3, category4,
            category5, category6, category7 );

        return categoryList;
    };

    @Override
    public Map<String, Category> getTrailersMap() {
        Map<String, Category> trailersCategories = new HashMap<String, Category>();

        this.getAllCategoriesObjects().forEach( category -> {
            trailersCategories.put( category.getId(), category);
        } );

        return trailersCategories;
    };
    
}
