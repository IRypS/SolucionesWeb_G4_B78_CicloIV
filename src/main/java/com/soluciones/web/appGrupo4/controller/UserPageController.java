package com.soluciones.web.appGrupo4.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soluciones.web.appGrupo4.model.Trailer;
import com.soluciones.web.appGrupo4.model.UserList;
import com.soluciones.web.appGrupo4.service.ICategoriesService;
import com.soluciones.web.appGrupo4.service.ICountryService;
import com.soluciones.web.appGrupo4.service.ILanguageService;
import com.soluciones.web.appGrupo4.service.IListService;
import com.soluciones.web.appGrupo4.service.ITrailerService;

@Controller
@RequestMapping("/app")
public class UserPageController {

    @Value("${web.title}")
    private String title;

    @Autowired
    private ITrailerService trailerInterface;

    @Autowired
    private ILanguageService languageInterface;

    @Autowired
    private ICategoriesService categoriesInterface;

    @Autowired
    private ICountryService countriesInterface;

    @Autowired
    private IListService listsInterface;
   
    @GetMapping("/trailers")
    public String allTrailers(Model model) {

        model.addAttribute("title", "Trailers | " + title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailersList", trailerInterface.getAllTrailers());
        model.addAttribute("languagesMap", languageInterface.getLanguagesMap());
        model.addAttribute("categoriesList", categoriesInterface.getAllCategories());
        model.addAttribute("countriesList", countriesInterface.getAllCountries());
 
        return "trailers";
    };

    @GetMapping("/trailer/view/{id}")
    public String trailerView(@PathVariable String id, Model model) {

        Map<String, Trailer> mapaTrailers = trailerInterface.getTrailersMap();
        Trailer targetTrailer = mapaTrailers.get(id);

        // Redirect when id from link is invalid
        if (targetTrailer == null) {
            return "redirect:/";
        }

        model.addAttribute("title", "Trailer View | " + title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailer", targetTrailer);
        model.addAttribute("relatedTrailers", trailerInterface.getRelatedTrailers(id));
        model.addAttribute("languagesMap", languageInterface.getLanguagesMap());
        model.addAttribute("userList", listsInterface.getAllLists());

 
        return "trailer_view";
    };


    @PostMapping("/list/createNewList/{id}")
    public String createNewList(UserList usrList, @PathVariable String id) {
        System.out.println("*** ---> Lista creada");
        System.out.println(usrList.getName());

        // Redirect to same page
        return "redirect:/app/trailer/view/" + id;
    };

}
