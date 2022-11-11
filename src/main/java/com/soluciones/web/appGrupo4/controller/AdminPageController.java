package com.soluciones.web.appGrupo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soluciones.web.appGrupo4.model.validators.V_Trailer;
import com.soluciones.web.appGrupo4.service.interfaces.ILanguageService;
import com.soluciones.web.appGrupo4.service.interfaces.IMovieService;
import com.soluciones.web.appGrupo4.service.interfaces.ITrailerService;



@Controller
@RequestMapping("/app/administrator")
public class AdminPageController {

    @Value("${web.title}")
    private String title;

    @Autowired
    private ITrailerService trailerInterface;

    @Autowired
    private IMovieService movieinterface;

    @Autowired
    private ILanguageService languageInterface;


    @GetMapping("/dashboard")
    public String dashboardHome(Model model){

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);
        
        return "admin/dashboard";
    }

    @GetMapping("/trailerList")
    public String getTrailerList(Model model) {

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailerList", trailerInterface.getAllTrailers());
        return "admin/trailer";
    }
    
    @GetMapping("/insert/trailer")
    public String trailerForm(Model model) {

        V_Trailer trailer = new V_Trailer();

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailer", trailer);
        model.addAttribute("lazyMovie", movieinterface.getLazyInfoTrailer());
        model.addAttribute("lazyLanguage", languageInterface.getLazyInfoLanguage());
        return "admin/trailer_form";
    }

    @PostMapping("/create/trailer")
    public String createTrailer(
            @Validated @ModelAttribute("trailer") V_Trailer trailer, 
            BindingResult br, Model model) {

        // Verify errors
        if(br.hasErrors()) { 
            model.addAttribute("lazyMovie", movieinterface.getLazyInfoTrailer());
            model.addAttribute("lazyLanguage", languageInterface.getLazyInfoLanguage());
            return "admin/trailer_form"; 
        };

        System.out.println("--| Resultados del trailer: |---");
        System.out.println(trailer.getId());
        System.out.println(trailer.getTitle());
        System.out.println(trailer.getViews());
        System.out.println(trailer.getLaguageId());
        System.out.println(trailer.getSubtitleId());
        System.out.println(trailer.getMovieId());
        
        String a = trailerInterface.createTrailer(trailer);
        System.out.println(a);

        return "redirect:/app/administrator/dashboard";
    }
    
    
}
