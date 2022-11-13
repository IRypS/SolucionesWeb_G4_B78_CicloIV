package com.soluciones.web.appGrupo4.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.validators.V_Trailer;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;
import com.soluciones.web.appGrupo4.service.interfaces.ILanguageService;
import com.soluciones.web.appGrupo4.service.interfaces.IMovieService;
import com.soluciones.web.appGrupo4.service.interfaces.IPersonService;
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
    private IPersonService personInterface;

    @Autowired
    private IGenreService genreService;

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

    @GetMapping("/update/form/trailer/{id}")
    public String trailerUpdateForm(@PathVariable String id, Model model) {

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailer", trailerInterface.getTrailerById(id));
        model.addAttribute("lazyMovie", movieinterface.getLazyInfoTrailer());
        model.addAttribute("lazyLanguage", languageInterface.getLazyInfoLanguage());

        return "admin/trailer_form";
    };
    
    @GetMapping("/delete/trailer/{id}")
    public String deleteTrailer(@PathVariable String id, Model model) {
        trailerInterface.deleteTrailerById(id);
        return "redirect:/app/administrator/trailerList";
    }

    @GetMapping("/movieList")
    public String getMovieList(Model model) {

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("movieList", movieinterface.getAllMovies());
        return "admin/movie";
    }

    @GetMapping("/insert/movie")
    public String movieAux(Model model) {

        E_Movie movie = new E_Movie();

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("movie", movie);
        model.addAttribute("lazyPerson", personInterface.getLazyInfoPerson());
        model.addAttribute("lazyGenre", genreService.getAllGenres());
        return "admin/movie_form";
    }

    @PostMapping("/create/movie")
    public String createAux(
            @Validated @ModelAttribute("movie") E_Movie movie, 
            BindingResult br, Model model, 
            @RequestParam(value = "idDirectors[]", required = false) List<String> idDirectors,
            @RequestParam(value = "idGenres[]", required = false) List<String> idGenres,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage) {

        // Verify errors
        if(br.hasErrors()) { 
            model.addAttribute("lazyPerson", personInterface.getLazyInfoPerson());
            model.addAttribute("lazyGenre", genreService.getAllGenres());
            return "admin/movie_form"; 
        };

        if (!coverImage.isEmpty()) {
			Path pathImage = Paths.get("src//main//resources//static//resources//upload//movie//cover");
			String pathGeneric = pathImage.toFile().getAbsolutePath();

			try {
				byte[] bytesCoverImg = coverImage.getBytes();
				Path completePath = Paths.get(pathGeneric + "//" + coverImage.getOriginalFilename());
				Files.write(completePath, bytesCoverImg);

				movie.setCoverUrl(coverImage.getOriginalFilename());
			} catch (Exception e) {

			}

		}

        movieinterface.createMovie(movie, idDirectors, idGenres);

        return "redirect:/app/administrator/movieList";
    }

    @GetMapping("/delete/movie/{id}")
    public String deleteMovie(@PathVariable String id, Model model) {
        movieinterface.deleteTrailerById(id);
        return "redirect:/app/administrator/movieList";
    }

    @GetMapping("/update/form/movie/{id}")
    public String movieUpdateForm(@PathVariable String id, Model model) {

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("movie", movieinterface.getMovieById(id));
        model.addAttribute("lazyPerson", personInterface.getLazyInfoPerson());
        model.addAttribute("lazyGenre", genreService.getAllGenres());

        return "admin/movie_form";
    };
}
