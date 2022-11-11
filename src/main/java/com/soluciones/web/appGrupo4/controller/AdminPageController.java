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

import com.soluciones.web.appGrupo4.model.validators.V_Movie;
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

        trailerInterface.createTrailer(trailer);

        return "redirect:/app/administrator/dashboard";
    }
    
    @GetMapping("/movieList")
    public String getMovieList(Model model) {

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("movieList", movieinterface.getAllMovies());
        return "admin/movie";
    }

    @GetMapping("/insert/movie")
    public String movieForm(Model model) {

        V_Movie movie = new V_Movie();

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("movie", movie);
        return "admin/movie_form";
    }

    @PostMapping("/create/movie")
    public String creteMovie(
            @Validated @ModelAttribute("movie") V_Movie movie, 
            BindingResult br, Model model) {

        // Verify errors
        if(br.hasErrors()) { 
            return "admin/movie_form"; 
        };

        System.out.println("--| Resultados del objeto |--");
        System.out.println(movie.getIdMovie());
        System.out.println(movie.getCoverUrl());
        System.out.println(movie.getName());
        System.out.println(movie.getDuration());
        System.out.println(movie.getSynopsis());
        System.out.println(movie.getReleaseDate());
        System.out.println(movie.getRate());

        movieinterface.createTrailer(movie);

        return "redirect:/app/administrator/dashboard";
    }
}
