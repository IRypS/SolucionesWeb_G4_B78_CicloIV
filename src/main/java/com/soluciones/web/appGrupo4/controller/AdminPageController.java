package com.soluciones.web.appGrupo4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;
import com.soluciones.web.appGrupo4.model.entities.E_User;
import com.soluciones.web.appGrupo4.model.validators.V_Language;
import com.soluciones.web.appGrupo4.model.validators.V_Movie;
import com.soluciones.web.appGrupo4.model.validators.V_Person;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;
import com.soluciones.web.appGrupo4.service.interfaces.ILanguageService;
import com.soluciones.web.appGrupo4.service.interfaces.IMovieService;
import com.soluciones.web.appGrupo4.service.interfaces.IPersonService;
import com.soluciones.web.appGrupo4.service.interfaces.IRolService;
import com.soluciones.web.appGrupo4.service.interfaces.ITrailerService;
import com.soluciones.web.appGrupo4.service.interfaces.IUserService;



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

    @Autowired
    private IUserService userService;

    @Autowired
    private IRolService rolService;

    @Value("${image.resource.path.general.cloud}")
    private String movieImagePath;


    @GetMapping("/dashboard")
    public String dashboardHome(Model model){

        model.addAttribute("title", title + " | Dashboard");
        
        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }
        
        return "admin/dashboard";
    }

    @GetMapping("/trailerList")
    public String getTrailerList(Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        Response<E_Trailer> response = trailerInterface.getAllTrailers();

        if (response.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Trailers");
			model.addAttribute("trailerList", response.getListData());
			return "admin/trailer";
		} else {
			model.addAttribute("title", title + " | Error al obtener trailers");
            model.addAttribute("status", "500");
			model.addAttribute("response", response.getMessage());
			model.addAttribute("errorMessage", response.getErrorMessage());
			return "error/500";
		}
    }
    
    @GetMapping("/insert/trailer")
    public String trailerForm(Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        E_Trailer trailer = new E_Trailer();
        E_Language lang = new E_Language();
        lang.setNameLanguage("");
        trailer.setLanguage(lang);
        trailer.setSubtitle(lang);

        Response<V_Movie> movieDataResponse = movieinterface.getLazyInfoMovie();
        Response<V_Language> languageDataResponse = languageInterface.getLazyInfoLanguage();

        model.addAttribute("title", title + " | Crear Trailer");
        model.addAttribute("trailer", trailer);

        if (movieDataResponse.getState()) {
			model.addAttribute("lazyMovie", movieDataResponse.getListData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
            model.addAttribute("status", "500");
			model.addAttribute("response", movieDataResponse.getMessage());
			model.addAttribute("errorMessage", movieDataResponse.getErrorMessage());
			return "error/500";
		}

        if (languageDataResponse.getState()) {
			model.addAttribute("lazyLanguage", languageDataResponse.getListData());
			return "admin/trailer_form";
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
            model.addAttribute("status", "500");
			model.addAttribute("response", languageDataResponse.getMessage());
			model.addAttribute("errorMessage", languageDataResponse.getErrorMessage());
			return "error/500";
		}
    }

    @PostMapping("/create/trailer")
    public String createTrailer(
            @Validated @ModelAttribute("trailer") E_Trailer trailer,
            BindingResult br, Model model,
            @RequestParam(value = "movieID", required = false) String movieID,
            @RequestParam(value = "languageID", required = false) String languageID,
            @RequestParam(value = "subtitleID", required = false) String subtitleID) {


        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        // Verify errors
        if(br.hasErrors()) { 
            Response<V_Movie> movieDataResponse = movieinterface.getLazyInfoMovie();
            Response<V_Language> languageDataResponse = languageInterface.getLazyInfoLanguage();

            model.addAttribute("lazyMovie", movieDataResponse.getListData());
            model.addAttribute("lazyLanguage", languageDataResponse.getListData());
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
            model.addAttribute("status", "500");
            model.addAttribute("response", createTrailerResponse.getMessage());
            model.addAttribute("errorMessage", createTrailerResponse.getErrorMessage());
            return "error/500";
        }

    }

    @GetMapping("/update/form/trailer/{id}")
    public String trailerUpdateForm(@PathVariable String id, Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        model.addAttribute("title", title);

        Response<E_Trailer> trailerResponse = trailerInterface.getTrailerById(id);
        Response<V_Movie> movieDataResponse = movieinterface.getLazyInfoMovie();
        Response<V_Language> languageDataResponse = languageInterface.getLazyInfoLanguage();

        if (trailerResponse.getState()) {
			model.addAttribute("trailer", trailerResponse.getData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
            model.addAttribute("status", "500");
			model.addAttribute("response", trailerResponse.getMessage());
			model.addAttribute("errorMessage", trailerResponse.getErrorMessage());
			return "error/500";
		}

        if (movieDataResponse.getState()) {
			model.addAttribute("lazyMovie", movieDataResponse.getListData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
            model.addAttribute("status", "500");
			model.addAttribute("response", movieDataResponse.getMessage());
			model.addAttribute("errorMessage", movieDataResponse.getErrorMessage());
			return "error/500";
		}

        if (languageDataResponse.getState()) {
			model.addAttribute("lazyLanguage", languageDataResponse.getListData());
			return "admin/trailer_form";
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
            model.addAttribute("status", "500");
			model.addAttribute("response", languageDataResponse.getMessage());
			model.addAttribute("errorMessage", languageDataResponse.getErrorMessage());
			return "error/500";
		}
    };
    
    @GetMapping("/delete/trailer/{id}")
    public String deleteTrailer(@PathVariable String id, Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        
        Response<E_Trailer> trailerDeleteResponse = trailerInterface.deleteTrailerById(id);

        if (trailerDeleteResponse.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Trailers");
			model.addAttribute("trailerList", trailerDeleteResponse.getListData());
			model.addAttribute("response", trailerDeleteResponse.getMessage());
			return "admin/trailer";
		} else {
			model.addAttribute("title", title + " | Error al eliminar el trailer");
            model.addAttribute("status", "500");
			model.addAttribute("response", trailerDeleteResponse.getMessage());
			model.addAttribute("errorMessage", trailerDeleteResponse.getErrorMessage());
			return "error/500";
		}

    }

    @GetMapping("/movieList")
    public String getMovieList(Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        Response<E_Movie> response = movieinterface.getAllMovies();

        if (response.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Peliculas");
			model.addAttribute("movieList", response.getListData());
            model.addAttribute("movieImagePath", movieImagePath);
			return "admin/movie";
		} else {
			model.addAttribute("title", title + " | Error al obtener peliculas");
            model.addAttribute("status", "500");
			model.addAttribute("response", response.getMessage());
			model.addAttribute("errorMessage", response.getErrorMessage());
			return "error/500";
		}

    }

    @GetMapping("/insert/movie")
    public String movieAux(Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        E_Movie movie = new E_Movie();
        Response<V_Person> personDataResponse = personInterface.getLazyInfoPerson();
        Response<E_Genre> genreDataResponse = genderInterface.getAllGenres();

        model.addAttribute("title", title + " | Crear Pelicula");
        model.addAttribute("movie", movie);

        if (personDataResponse.getState()) {
			model.addAttribute("lazyPerson", personDataResponse.getListData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
            model.addAttribute("status", "500");
			model.addAttribute("response", personDataResponse.getMessage());
			model.addAttribute("errorMessage", personDataResponse.getErrorMessage());
			return "error/500";
		}

        if (genreDataResponse.getState()) {
			model.addAttribute("lazyGenre", genreDataResponse.getListData());
			return "admin/movie_form";
		} else {
			model.addAttribute("title", title + " | Error en el formulario de trailer");
            model.addAttribute("status", "500");
			model.addAttribute("response", genreDataResponse.getMessage());
			model.addAttribute("errorMessage", genreDataResponse.getErrorMessage());
            return "error/500";
        }
    }

    @PostMapping("/create/movie")
    public String createAux(
            @Validated @ModelAttribute("movie") E_Movie movie, 
            BindingResult br, Model model, 
            @RequestParam(value = "idDirectors[]", required = false) List<String> idDirectors,
            @RequestParam(value = "idGenres[]", required = false) List<String> idGenres,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage) {



        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

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

        if ( (movieinterface.movieExists(movie.getName())) && movie.getIdMovie().isBlank() ) {
            br.addError( new FieldError("movie", "name", "La pelicula ya existe en la base de datos"));
        }

        // Verify errors
        if(br.hasErrors()) { 
            model.addAttribute("lazyPerson", personDataResponse.getListData());
            model.addAttribute("lazyGenre", genreDataResponse.getListData());
            return "admin/movie_form"; 
        };
        

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
                model.addAttribute("errorMessage", createTrailerResponse.getErrorMessage());
				return "admin/movie_form";

			} else {
				model.addAttribute("title", title + " | Error al crear/editar la pelicula");
                model.addAttribute("status", "500");
                model.addAttribute("response", createTrailerResponse.getMessage());
                model.addAttribute("errorMessage", createTrailerResponse.getErrorMessage());
                return "error/500";
			}
		}

    }

    @GetMapping("/update/form/movie/{id}")
    public String movieUpdateForm(@PathVariable String id, Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        model.addAttribute("title", title);

        Response<E_Movie> movieDataResponse = movieinterface.getMovieById(id);
        Response<V_Person> personDataResponse = personInterface.getLazyInfoPerson();
        Response<E_Genre> genreDataResponse = genderInterface.getAllGenres();

        if (movieDataResponse.getState()) {
			model.addAttribute("movie", movieDataResponse.getData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de pelicula");
            model.addAttribute("status", "500");
			model.addAttribute("response", movieDataResponse.getMessage());
			model.addAttribute("errorMessage", movieDataResponse.getErrorMessage());
			return "error/500";
		}

        if (personDataResponse.getState()) {
			model.addAttribute("lazyPerson", personDataResponse.getListData());
		} else {
			model.addAttribute("title", title + " | Error en el formulario de pelicula");
            model.addAttribute("status", "500");
			model.addAttribute("response", personDataResponse.getMessage());
			model.addAttribute("errorMessage", personDataResponse.getErrorMessage());
			return "error/500";
		}

        if (genreDataResponse.getState()) {
			model.addAttribute("lazyGenre", genreDataResponse.getListData());
			return "admin/movie_form";
		} else {
			model.addAttribute("title", title + " | Error en el formulario de pelicula");
            model.addAttribute("status", "500");
			model.addAttribute("response", genreDataResponse.getMessage());
			model.addAttribute("errorMessage", genreDataResponse.getErrorMessage());
			return "error/500";
		}

    };

    @GetMapping("/delete/movie/{id}")
    public String deleteMovie(@PathVariable String id, Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }
        
        Response<E_Movie> movieDeleteResponse = movieinterface.deleteMovieById(id);

        if (movieDeleteResponse.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Peliculas");
			model.addAttribute("movieList", movieDeleteResponse.getListData());
            model.addAttribute("movieImagePath", movieImagePath);
			model.addAttribute("response", movieDeleteResponse.getMessage());
			return "admin/movie";

		} else {
			model.addAttribute("title", title + " | Error al eliminar la pelicula");
            
            if (movieDeleteResponse.getErrorMessage() == "ERROR SQL-CONSTRAINT-VIOLATION") {
                model.addAttribute("response", movieDeleteResponse.getMessage());
                model.addAttribute("errorMessage", movieDeleteResponse.getErrorMessage());
                model.addAttribute("movie", movieDeleteResponse.getData());

                String idMovie = movieDeleteResponse.getData().getIdMovie();
                model.addAttribute("trailerList", trailerInterface.getTrailersByIdMovie(idMovie).getListData());
                return "admin/movie_delete";

            } else {
                model.addAttribute("status", "500");
                model.addAttribute("response", movieDeleteResponse.getMessage());
                model.addAttribute("errorMessage", movieDeleteResponse.getErrorMessage());
                return "error/500";
            }
            
		}
    }

    @PostMapping("/delete/onlymovie/{id}")
    public String deleteOnlyMovie( Model model, @PathVariable String id,
        @RequestParam(value = "idTrailers[]", required = false) List<String> idTrailers) {
        

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        
        Response<E_Trailer> deleteMovieFromTrailer = trailerInterface.deleteMovieFromTrailer(idTrailers);

        if (deleteMovieFromTrailer.getState()) {

            Response<E_Movie> deleteMovie = movieinterface.deleteMovieById(id);
            if (deleteMovie.getState()) {
                model.addAttribute("title", title + " | (ADMIN) Listado de Peliculas");
                model.addAttribute("movieList", deleteMovie.getListData());
                model.addAttribute("movieImagePath", movieImagePath);
                model.addAttribute("response", deleteMovie.getMessage());
                return "admin/movie";
            } else {
                model.addAttribute("title", title + " | Error al eliminar la pelicula");
                model.addAttribute("status", "500");
                model.addAttribute("response", deleteMovie.getMessage());
                model.addAttribute("errorMessage", deleteMovie.getErrorMessage());
                return "error/500";
            }

        } else {
            model.addAttribute("title", title + " | Error al eliminar la pelicula de los trailers");
            model.addAttribute("status", "500");
            model.addAttribute("response", deleteMovieFromTrailer.getMessage());
            model.addAttribute("errorMessage", deleteMovieFromTrailer.getErrorMessage());
            return "error/500";
        }

    }

    @PostMapping("/delete/movieandtrailers/{id}")
    public String deleteAllFromMovie( Model model, @PathVariable String id,
        @RequestParam(value = "idTrailers[]", required = false) List<String> idTrailers) {
        

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        Response<E_Movie> deleteMovie = movieinterface.deleteMovieAndTrailers(id, idTrailers);

        if (deleteMovie.getState()) {
            model.addAttribute("title", title + " | (ADMIN) Listado de Peliculas");
            model.addAttribute("movieList", deleteMovie.getListData());
            model.addAttribute("movieImagePath", movieImagePath);
            model.addAttribute("response", deleteMovie.getMessage());
            return "admin/movie";
            
        } else {
            model.addAttribute("title", title + " | Error al eliminar la pelicula");
            model.addAttribute("status", "500");
            model.addAttribute("response", deleteMovie.getMessage());
            model.addAttribute("errorMessage", deleteMovie.getErrorMessage());
            return "error/500";
        }

    }

    @GetMapping("/genreList")
    public String getGenreList(Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }


        Response<E_Genre> response = genderInterface.getAllGenres();

        if (response.getState()) {
			model.addAttribute("title", title + " | (ADMIN) Listado de Generos");
			model.addAttribute("genreList", response.getListData());
			return "admin/genre";
		} else {
			model.addAttribute("title", title + " | Error al obtener generos");
            model.addAttribute("status", "500");
			model.addAttribute("response", response.getMessage());
			model.addAttribute("errorMessage", response.getErrorMessage());
			return "error/500";
		}
    }

    @GetMapping("/insert/genre")
    public String genreForm(Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        E_Genre genre = new E_Genre();

        model.addAttribute("title", title + " | Crear Genero");
        model.addAttribute("genre", genre);

        return "admin/genre_form";
    }

    @PostMapping("/create/genre")
    public String createGenre( 
        @Validated @ModelAttribute("genre") E_Genre genre,
        BindingResult br, Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        if ( (genderInterface.genreExists(genre.getNameGenre())) && genre.getIdGenre().isBlank() ) {
            br.addError( new FieldError("genre", "nameGenre", "El género ya existe en la base de datos"));
        }

        // Verify errors
        if(br.hasErrors()) { 
            return "admin/genre_form"; 
        };


        Response<E_Genre> createGenreResponse = genderInterface.createGenre(genre);

        if (createGenreResponse.getState()) {
            model.addAttribute("title", title + " | (ADMIN) Listado de Géneros");
			model.addAttribute("genreList", createGenreResponse.getListData());
			model.addAttribute("response", createGenreResponse.getMessage());
            return "admin/genre";
        } else {
            model.addAttribute("title", title + " | Error en el formulario de generos");
            model.addAttribute("status", "500");
            model.addAttribute("response", createGenreResponse.getMessage());
            model.addAttribute("errorMessage", createGenreResponse.getErrorMessage());
            return "error/500";
        }

    }

    @GetMapping("/update/form/genre/{id}")
    public String genreUpdateForm(@PathVariable String id, Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        model.addAttribute("title", title);

        Response<E_Genre> genreResponse = genderInterface.getGenreById(id);

        if (genreResponse.getState()) {
            model.addAttribute("genre", genreResponse.getData());
            return "admin/genre_form";
        } else {
            model.addAttribute("title", title + " | Error en el formulario de genero");
            model.addAttribute("status", "500");
            model.addAttribute("response", genreResponse.getMessage());
            model.addAttribute("errorMessage", genreResponse.getErrorMessage());
            return "error/500";
        }

    };

    @GetMapping("/delete/genre/{id}")
    public String deleteGenre(@PathVariable String id, Model model) {

        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }
        
        Response<E_Genre> genreDeleteResponse = genderInterface.deleteGenderById(id);

        if (genreDeleteResponse.getState()) {
            model.addAttribute("title", title + " | (ADMIN) Listado de Peliculas");
            model.addAttribute("genreList", genreDeleteResponse.getListData());
            model.addAttribute("response", genreDeleteResponse.getMessage());
            return "admin/genre";

        } else {
            model.addAttribute("title", title + " | Error al eliminar la pelicula");

            if (genreDeleteResponse.getErrorMessage() == "ERROR SQL-CONSTRAINT-VIOLATION") {
                model.addAttribute("response", genreDeleteResponse.getMessage());
                model.addAttribute("errorMessage", genreDeleteResponse.getErrorMessage());
                model.addAttribute("genre", genreDeleteResponse.getData());

                String idGenre = genreDeleteResponse.getData().getIdGenre();
                model.addAttribute("moviesIdList", movieinterface.getIdMoviesByGenreId(idGenre));
                model.addAttribute("moviesCount", movieinterface.getMoviesCountByGenreId(idGenre));
                return "admin/genre_delete";

            } else {
                model.addAttribute("status", "500");
                model.addAttribute("response", genreDeleteResponse.getMessage());
                model.addAttribute("errorMessage", genreDeleteResponse.getErrorMessage());
                return "error/500";
            }

        }
    }

    @PostMapping("/delete/genre/force/{id}")
    public String forceDeleteGenre( Model model, @PathVariable String id,
        @RequestParam(value = "idMovies[]", required = false) List<String> idMovies) {

            
        Response<E_User> userDataResponse = userService.getUserInfo();

        if (userDataResponse.getState()) {
            model.addAttribute("activeSession", true);
            model.addAttribute("userObject", userDataResponse.getData());
            model.addAttribute("isAdmin", rolService.isAdmin(userDataResponse.getData().getRoles()));
        } else {
            model.addAttribute("activeSession", false);
        }

        Response<E_Movie> deleteGenreFromMovies = movieinterface.deleteGenreFromMovies(id, idMovies);

        if (deleteGenreFromMovies.getState()) {

            Response<E_Genre> deleteGenre = genderInterface.deleteGenderById(id);

            if (deleteGenre.getState()) {
                model.addAttribute("title", title + " | (ADMIN) Listado de Generos");
                model.addAttribute("genreList", deleteGenre.getListData());
                model.addAttribute("response", deleteGenre.getMessage());
                return "admin/genre";

            } else {
                model.addAttribute("title", title + " | Error al eliminar el género");
                model.addAttribute("status", "500");
                model.addAttribute("response", deleteGenre.getMessage());
                model.addAttribute("errorMessage", deleteGenre.getErrorMessage());
                return "error/500";
            }

        } else {
            model.addAttribute("title", title + " | Error al eliminar el género de las peliculas");
            model.addAttribute("status", "500");
            model.addAttribute("response", deleteGenreFromMovies.getMessage());
            model.addAttribute("errorMessage", deleteGenreFromMovies.getErrorMessage());
            return "error/500";
        }
    }

}
