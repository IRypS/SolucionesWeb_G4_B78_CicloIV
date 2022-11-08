package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soluciones.web.appGrupo4.model.UserList;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;
import com.soluciones.web.appGrupo4.service.ICountryService;
import com.soluciones.web.appGrupo4.service.IListService;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;
import com.soluciones.web.appGrupo4.service.interfaces.ILanguageService;
import com.soluciones.web.appGrupo4.service.interfaces.ITrailerService;

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
    private IGenreService genreInterface;

    @Autowired
    private ICountryService countriesInterface;

    @Autowired
    private IListService listsInterface;
   
    @GetMapping("/trailers")
    public String allTrailers(Model model) {

        model.addAttribute("title", "Trailers | " + title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailersList", trailerInterface.getAllTrailers());
        model.addAttribute("languagesList", languageInterface.getAllLanguages());
        model.addAttribute("genresList", genreInterface.getAllGenres());
        model.addAttribute("countriesList", countriesInterface.getAllCountries());
 
        return "trailers";
    };

    @GetMapping("/trailer/view/{id}")
    public String trailerView(@PathVariable String id, Model model) {

        E_Trailer targetTrailer = new E_Trailer();

        try {
            targetTrailer = trailerInterface.getTargetTrailer(id);
        } catch (Exception e){
            return "redirect:/";
        }

        model.addAttribute("title", "Trailer View | " + title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailer", targetTrailer);
        model.addAttribute("relatedTrailers", trailerInterface.getRelatedTrailers(id));
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
