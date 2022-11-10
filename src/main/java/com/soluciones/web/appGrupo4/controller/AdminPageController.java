package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soluciones.web.appGrupo4.service.interfaces.ITrailerService;


@Controller
@RequestMapping("/app/administrator")
public class AdminPageController {

    @Value("${web.title}")
    private String title;

    @Autowired
    private ITrailerService trailerInterface;


    @GetMapping("/dashboard")
    public String dashboardHome(Model model){

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);
        
        return "admin/dashboard";
    }

    @GetMapping("trailerList")
    public String getMethodName(Model model) {

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailerList", trailerInterface.getAllTrailers());
        return "admin/trailer";
    }
    
    
}
