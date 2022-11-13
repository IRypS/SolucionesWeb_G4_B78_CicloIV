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

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;
import com.soluciones.web.appGrupo4.model.validators.V_Language;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;
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

        // model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        Response<E_Trailer> response = trailerInterface.getAllTrailers();

        if (response.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Trailers");
			model.addAttribute("trailerList", response.getListData());
			// model.addAttribute("response", response.getMessage());
			return "admin/trailer";
		} else {
			model.addAttribute("title", title + " | Error al obtener trailers");
			model.addAttribute("response", response.getMessage());
			model.addAttribute("error", response.getErrorMessage());
			return "admin/errors";
		}
        // model.addAttribute("trailerList", trailerInterface.getAllTrailers());
        // return "admin/trailer";
    }
    
    @GetMapping("/insert/trailer")
    public String trailerForm(Model model) {

        E_Trailer trailer = new E_Trailer();
        Response<V_Movie> movieDataResponse = movieinterface.getLazyInfoMovie();
        Response<V_Language> languageDataResponse = languageInterface.getLazyInfoLanguage();

        // model.addAttribute("title", title);
        model.addAttribute("activeSession", true);
        model.addAttribute("title", title + " | Crear Trailer");
        model.addAttribute("trailer", trailer);

        if (movieDataResponse.getState()) {
			model.addAttribute("lazyMovie", movieDataResponse.getListData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
			model.addAttribute("response", movieDataResponse.getMessage());
			model.addAttribute("error", movieDataResponse.getErrorMessage());
			return "admin/errors";
		}

        if (languageDataResponse.getState()) {
			model.addAttribute("lazyLanguage", languageDataResponse.getListData());
			return "admin/trailer_form";
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
			model.addAttribute("response", languageDataResponse.getMessage());
			model.addAttribute("error", languageDataResponse.getErrorMessage());
			return "admin/errors";
		}

        // model.addAttribute("trailer", trailer);
        // model.addAttribute("lazyMovie", movieinterface.getLazyInfoMovie());
        // model.addAttribute("lazyLanguage", languageInterface.getLazyInfoLanguage());
        // return "admin/trailer_form";
    }

    @PostMapping("/create/trailer")
    public String createTrailer(
            @Validated @ModelAttribute("trailer") E_Trailer trailer,
            BindingResult br, Model model,
            @RequestParam(value = "movieID", required = false) String movieID,
            @RequestParam(value = "languageID", required = false) String languageID,
            @RequestParam(value = "subtitleID", required = false) String subtitleID) {

        // Verify errors
        if(br.hasErrors()) { 
            model.addAttribute("lazyMovie", movieinterface.getLazyInfoMovie());
            model.addAttribute("lazyLanguage", languageInterface.getLazyInfoLanguage());
            return "admin/trailer_form"; 
        };

        Response<E_Trailer> createTrailerResponse = trailerInterface
            .createTrailer(trailer, movieID, languageID, subtitleID);

        if (createTrailerResponse.getState()) {
            model.addAttribute("title", title + " | (ADMIN) Listado de Trailers");
			model.addAttribute("trailerList", createTrailerResponse.getListData());
			model.addAttribute("response", createTrailerResponse.getMessage());
            return "admin/trailer";
        } else {
            model.addAttribute("title", title + " | Error en el formulario de trailer");
            model.addAttribute("response", createTrailerResponse.getMessage());
            model.addAttribute("error", createTrailerResponse.getErrorMessage());
            return "admin/errors";
        }

    }

    @GetMapping("/update/form/trailer/{id}")
    public String trailerUpdateForm(@PathVariable String id, Model model) {

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        model.addAttribute("trailer", trailerInterface.getTrailerById(id));
        model.addAttribute("lazyMovie", movieinterface.getLazyInfoMovie());
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
