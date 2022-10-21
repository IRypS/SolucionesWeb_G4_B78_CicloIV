package com.soluciones.web.appGrupo4.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soluciones.web.appGrupo4.model.Trailer;
import com.soluciones.web.appGrupo4.service.ITrailerService;

@Controller
@RequestMapping("/app")
public class UserPageController {

    @Value("${web.title}")
    private String title;

    @Autowired
    private ITrailerService trailerInterface;
   
    @GetMapping("/trailers")
    public String allTrailers(Model model) {

        model.addAttribute("title", "Trailers | " + title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailersList", trailerInterface.getAllTrailers());
 
        return "trailers";
    };

    @GetMapping("/trailer/view/{id}")
    public String trailerView(@PathVariable String id, Model model) {

        Map<String, Trailer> mapaTrailers = trailerInterface.getTrailersMap();
        Trailer targetTrailer = mapaTrailers.get(id);

        System.out.println(trailerInterface.getRelatedTrailers().get(1).getImageUrl());   

        model.addAttribute("title", "Trailer View | " + title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailer", targetTrailer);
        model.addAttribute("relatedTrailers", trailerInterface.getRelatedTrailers());
 
        return "trailer_view";
    };

}
