package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${web.title}")
    private String title;
    
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("title", "Home | " + title);
        model.addAttribute("titleHero", title);
        model.addAttribute("activeSession", false);

        return "home";
    };
}
