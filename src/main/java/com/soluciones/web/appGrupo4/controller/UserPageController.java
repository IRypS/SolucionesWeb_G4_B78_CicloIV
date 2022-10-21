package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class UserPageController {

    @Value("${web.title}")
    private String title;
   
    @GetMapping("/trailers")
    public String allTrailers(Model model) {

        model.addAttribute("title", "Trailers | " + title);
        model.addAttribute("activeSession", true);
 
        return "trailers";
    };

    @GetMapping("/trailer/view")
    public String trailerView(Model model) {

        model.addAttribute("title", "Trailer View | " + title);
        model.addAttribute("activeSession", true);
 
        return "trailer_view";
    };

}
