package com.soluciones.web.appGrupo4.controller;

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
import com.soluciones.web.appGrupo4.model.entities.E_Genre;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;
import com.soluciones.web.appGrupo4.model.validators.V_Language;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;
import com.soluciones.web.appGrupo4.model.validators.V_Person;
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
    private IGenreService genderInterface;

    @Autowired
    private ILanguageService languageInterface;

    @Value("${image.resource.path.general}" + "${image.folder.movie.name}" + "/")
    private String movieImagePath;


    @GetMapping("/dashboard")
    public String dashboardHome(Model model){

        model.addAttribute("title", title + " | Dashboard");
        model.addAttribute("activeSession", true);
        
        return "admin/dashboard";
    }

    @GetMapping("/trailerList")
    public String getTrailerList(Model model) {

        model.addAttribute("activeSession", true);

        Response<E_Trailer> response = trailerInterface.getAllTrailers();

        if (response.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Trailers");
			model.addAttribute("trailerList", response.getListData());
			return "admin/trailer";
		} else {
			model.addAttribute("title", title + " | Error al obtener trailers");
			model.addAttribute("response", response.getMessage());
			model.addAttribute("error", response.getErrorMessage());
			return "admin/errors";
		}
    }
    
    @GetMapping("/insert/trailer")
    public String trailerForm(Model model) {

        E_Trailer trailer = new E_Trailer();
        Response<V_Movie> movieDataResponse = movieinterface.getLazyInfoMovie();
        Response<V_Language> languageDataResponse = languageInterface.getLazyInfoLanguage();

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
    }

    @PostMapping("/create/trailer")
    public String createTrailer(
            @Validated @ModelAttribute("trailer") E_Trailer trailer,
            BindingResult br, Model model,
            @RequestParam(value = "movieID", required = false) String movieID,
            @RequestParam(value = "languageID", required = false) String languageID,
            @RequestParam(value = "subtitleID", required = false) String subtitleID) {

        
        Response<V_Movie> movieDataResponse = movieinterface.getLazyInfoMovie();
        Response<V_Language> languageDataResponse = languageInterface.getLazyInfoLanguage();

        // Verify errors
        if(br.hasErrors()) { 
            model.addAttribute("lazyMovie", movieDataResponse.getListData());
            model.addAttribute("lazyLanguage", languageDataResponse.getListData());
            return "admin/trailer_form"; 
        };

        model.addAttribute("activeSession", true);

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

        Response<E_Trailer> trailerResponse = trailerInterface.getTrailerById(id);
        Response<V_Movie> movieDataResponse = movieinterface.getLazyInfoMovie();
        Response<V_Language> languageDataResponse = languageInterface.getLazyInfoLanguage();

        if (trailerResponse.getState()) {
			model.addAttribute("trailer", trailerResponse.getData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
			model.addAttribute("response", trailerResponse.getMessage());
			model.addAttribute("error", trailerResponse.getErrorMessage());
			return "admin/errors";
		}

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
    };
    
    @GetMapping("/delete/trailer/{id}")
    public String deleteTrailer(@PathVariable String id, Model model) {

        model.addAttribute("activeSession", true);
        
        Response<E_Trailer> trailerDeleteResponse = trailerInterface.deleteTrailerById(id);

        if (trailerDeleteResponse.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Trailers");
			model.addAttribute("trailerList", trailerDeleteResponse.getListData());
			model.addAttribute("response", trailerDeleteResponse.getMessage());
			return "admin/trailer";
		} else {
			model.addAttribute("title", title + " | Error al eliminar el trailer");
			model.addAttribute("response", trailerDeleteResponse.getMessage());
			model.addAttribute("error", trailerDeleteResponse.getErrorMessage());
			return "admin/errors";
		}

    }

    @GetMapping("/movieList")
    public String getMovieList(Model model) {

        model.addAttribute("activeSession", true);

        Response<E_Movie> response = movieinterface.getAllMovies();

        if (response.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Peliculas");
			model.addAttribute("movieList", response.getListData());
            model.addAttribute("movieImagePath", movieImagePath);
			return "admin/movie";
		} else {
			model.addAttribute("title", title + " | Error al obtener peliculas");
			model.addAttribute("response", response.getMessage());
			model.addAttribute("error", response.getErrorMessage());
			return "admin/errors";
		}

    }

    @GetMapping("/insert/movie")
    public String movieAux(Model model) {

        E_Movie movie = new E_Movie();
        Response<V_Person> personDataResponse = personInterface.getLazyInfoPerson();
        Response<E_Genre> genreDataResponse = genderInterface.getAllGenres();

        model.addAttribute("activeSession", true);
        model.addAttribute("title", title + " | Crear Pelicula");
        model.addAttribute("movie", movie);

        if (personDataResponse.getState()) {
			model.addAttribute("lazyPerson", personDataResponse.getListData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
			model.addAttribute("response", personDataResponse.getMessage());
			model.addAttribute("error", personDataResponse.getErrorMessage());
			return "admin/errors";
		}

        if (genreDataResponse.getState()) {
			model.addAttribute("lazyGenre", genreDataResponse.getListData());
			return "admin/movie_form";
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
			model.addAttribute("response", genreDataResponse.getMessage());
			model.addAttribute("error", genreDataResponse.getErrorMessage());
            return "admin/errors";
        }
    }

    @PostMapping("/create/movie")
    public String createAux(
            @Validated @ModelAttribute("movie") E_Movie movie, 
            BindingResult br, Model model, 
            @RequestParam(value = "idDirectors[]", required = false) List<String> idDirectors,
            @RequestParam(value = "idGenres[]", required = false) List<String> idGenres,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage) {


        Response<V_Person> personDataResponse = personInterface.getLazyInfoPerson();
        Response<E_Genre> genreDataResponse = genderInterface.getAllGenres();

        Response<E_Movie> movieResponse = movieinterface.getMovieById(movie.getIdMovie());
        if (movieResponse.getState()) {
            movie.setCoverUrl(movieResponse.getData().getCoverUrl());
            movie.setDirectorsList(movieResponse.getData().getDirectorsList());
            movie.setGenreList(movieResponse.getData().getGenreList());
        } else {
            movie.setCoverUrl(movie.getCoverUrl() + "");
        }

        // Verify errors
        if(br.hasErrors()) { 
            model.addAttribute("lazyPerson", personDataResponse.getListData());
            model.addAttribute("lazyGenre", genreDataResponse.getListData());
            return "admin/movie_form"; 
        };

        model.addAttribute("activeSession", true);
        

        if(coverImage.isEmpty() && movie.getCoverUrl().length() == 0) {
            model.addAttribute("title", title + " | Image not found");
            model.addAttribute("response", "La pelicula requiere una imagen");
            model.addAttribute("lazyPerson", personDataResponse.getListData());
            model.addAttribute("lazyGenre", genreDataResponse.getListData());
            return "admin/movie_form";
        }

        model.addAttribute("title", title);

        Response<E_Movie> createTrailerResponse = movieinterface.createMovie(movie, coverImage, idDirectors, idGenres);

        if (createTrailerResponse.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Peliculas");
			model.addAttribute("movieList", createTrailerResponse.getListData());
            model.addAttribute("movieImagePath", movieImagePath);
			model.addAttribute("response", createTrailerResponse.getMessage());
			return "admin/movie";
		} else {

            if (createTrailerResponse.getMessage().equals("IMG-ERROR")) {
                model.addAttribute("response", createTrailerResponse.getMessage());
                model.addAttribute("error", createTrailerResponse.getErrorMessage());
				return "admin/movie_form";

			} else {
				model.addAttribute("title", title + " | Error al crear/editar la pelicula");
                model.addAttribute("response", createTrailerResponse.getMessage());
                model.addAttribute("error", createTrailerResponse.getErrorMessage());
                return "admin/errors";
			}
		}

    }

    @GetMapping("/update/form/movie/{id}")
    public String movieUpdateForm(@PathVariable String id, Model model) {

        model.addAttribute("title", title);
        model.addAttribute("activeSession", true);

        Response<E_Movie> movieDataResponse = movieinterface.getMovieById(id);
        Response<V_Person> personDataResponse = personInterface.getLazyInfoPerson();
        Response<E_Genre> genreDataResponse = genderInterface.getAllGenres();

        if (movieDataResponse.getState()) {
			model.addAttribute("movie", movieDataResponse.getData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de pelicula");
			model.addAttribute("response", movieDataResponse.getMessage());
			model.addAttribute("error", movieDataResponse.getErrorMessage());
			return "admin/errors";
		}

        if (personDataResponse.getState()) {
			model.addAttribute("lazyPerson", personDataResponse.getListData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de pelicula");
			model.addAttribute("response", personDataResponse.getMessage());
			model.addAttribute("error", personDataResponse.getErrorMessage());
			return "admin/errors";
		}

        if (genreDataResponse.getState()) {
			model.addAttribute("lazyGenre", genreDataResponse.getListData());
			return "admin/movie_form";
		} else {
			model.addAttribute("title", title + " | Error en el formulario de pelicula");
			model.addAttribute("response", genreDataResponse.getMessage());
			model.addAttribute("error", genreDataResponse.getErrorMessage());
			return "admin/errors";
		}

    };

    @GetMapping("/delete/movie/{id}")
    public String deleteMovie(@PathVariable String id, Model model) {

        model.addAttribute("activeSession", true);
        
        Response<E_Movie> movieDeleteResponse = movieinterface.deleteTrailerById(id);

        if (movieDeleteResponse.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Peliculas");
			model.addAttribute("movieList", movieDeleteResponse.getListData());
            model.addAttribute("movieImagePath", movieImagePath);
			model.addAttribute("response", movieDeleteResponse.getMessage());
			return "admin/movie";
		} else {
			model.addAttribute("title", title + " | Error al eliminar la pelicula");
			model.addAttribute("response", movieDeleteResponse.getMessage());
			model.addAttribute("error", movieDeleteResponse.getErrorMessage());
			return "admin/errors";
		}
    }
}
