package com.soluciones.web.appGrupo4.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soluciones.web.appGrupo4.helper.IPaginationHelper;
import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.UserList;
import com.soluciones.web.appGrupo4.model.entities.E_Country;
import com.soluciones.web.appGrupo4.model.entities.E_Genre;
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.entities.E_Trailer;
import com.soluciones.web.appGrupo4.service.IListService;
import com.soluciones.web.appGrupo4.service.interfaces.ICountryService;
import com.soluciones.web.appGrupo4.service.interfaces.IGenreService;
import com.soluciones.web.appGrupo4.service.interfaces.ILanguageService;
import com.soluciones.web.appGrupo4.service.interfaces.ITrailerService;

@Controller
@RequestMapping("/app")
public class UserPageController {

    @Value("${web.title}")
    private String title;

    @Value("${image.resource.path.general.cloud}")
    private String movieImagePath;

    @Value("${pagination.user.size}")
    private int elementsPerPage;

    @Autowired
    private ITrailerService trailerInterface;

    @Autowired
    private ILanguageService languageInterface;

    @Autowired
    private IGenreService genreInterface;

    @Autowired
    private ICountryService countriesInterface;

    @Autowired
    private IListService listsInterface;

    @Autowired
    private IPaginationHelper pagination;
   
    @GetMapping("/trailers")
    public String paginatedTrailers(@RequestParam Map<String, Object> params, Model model) {

        model.addAttribute("activeSession", true);
        model.addAttribute("title", title + " | Trailers");

        Response<E_Trailer> trailerResponsePaginated = trailerInterface.getPaginatedTrailers(
                                        pagination.getPageNumberIndex(params.get("page")), 
                                        elementsPerPage);

        if (trailerResponsePaginated.getState()) {
			model.addAttribute("trailersList", trailerResponsePaginated
                                                                .getPaginatedData().getContent());
            model.addAttribute("pages", trailerResponsePaginated.getTotalPagesList());
            model.addAttribute("pagesInfo", trailerResponsePaginated.getPagesInformation());
			model.addAttribute("response", trailerResponsePaginated.getMessage());

        } else {
            model.addAttribute("title", title + " | Error al obtener Trailers");
            model.addAttribute("error", "Bad request");
            model.addAttribute("status", "400");
            model.addAttribute("response", trailerResponsePaginated.getMessage());
			model.addAttribute("errorMessage", trailerResponsePaginated.getErrorMessage());
			return "error/400";
		}

        Response<E_Language> languageResponse = languageInterface.getAllLanguages();
        if (languageResponse.getState()) {
            model.addAttribute("languagesList", languageResponse.getListData());
        } else {
            model.addAttribute("title", title + " | Error al obtener Lenguajes");
            model.addAttribute("status", "500");
            model.addAttribute("response", languageResponse.getMessage());
			model.addAttribute("errorMessage", languageResponse.getErrorMessage());
            return "error/500";
        }

        Response<E_Genre> genreResponse = genreInterface.getAllGenres();
        if (genreResponse.getState()) {
            model.addAttribute("genresList", genreResponse.getListData());
        } else {
            model.addAttribute("title", title + " | Error al obtener Generos");
            model.addAttribute("status", "500");
            model.addAttribute("response", genreResponse.getMessage());
			model.addAttribute("errorMessage", genreResponse.getErrorMessage());
            return "error/500";
        }

        Response<E_Country> countryResponse = countriesInterface.getAllCountries();
        if (countryResponse.getState()) {
            model.addAttribute("countriesList", countryResponse.getListData());
            return "trailers";
        } else {
            model.addAttribute("title", title + " | Error al obtener Paises");
            model.addAttribute("status", "500");
            model.addAttribute("response", countryResponse.getMessage());
			model.addAttribute("errorMessage", countryResponse.getErrorMessage());
            return "error/500";
        }
    };

    @GetMapping("/trailer/view/{id}")
    public String trailerView(@PathVariable String id, Model model) {

        model.addAttribute("activeSession", true);
        model.addAttribute("movieImagePath", movieImagePath);

        Response<E_Trailer> trailerResponse = trailerInterface.getTrailerById(id);
        if (trailerResponse.getState()) {
            
            trailerInterface.addView(trailerResponse.getData());
            model.addAttribute("title", title + " | " + trailerResponse.getData().getTitle());
			model.addAttribute("trailer", trailerResponse.getData());
            model.addAttribute("response", trailerResponse.getMessage());
        } else {
            model.addAttribute("title", title + " | Error al obtener el Trailer | ID:" + id);
            model.addAttribute("status", "404");
            model.addAttribute("error", "Not Found");
            model.addAttribute("response", trailerResponse.getMessage());
			// model.addAttribute("errorMessage", trailerResponse.getErrorMessage());

            return "error/404";
        }

        Response<E_Trailer> relatedTrailersResponse = trailerInterface.getRelatedTrailers(id);
        if (relatedTrailersResponse.getState()) {
            model.addAttribute("relatedTrailers", relatedTrailersResponse.getListData());
            // TemporallyData
            model.addAttribute("userList", listsInterface.getAllLists());

			return "trailer_view";

		} else {
            model.addAttribute("title", title + " | Error al obtener los trailers relacionados");
            model.addAttribute("status", "500");
            model.addAttribute("response", relatedTrailersResponse.getMessage());
			model.addAttribute("errorMessage", relatedTrailersResponse.getErrorMessage());
			return "error/500";
		}
    };


    @GetMapping("/search/trailer")
    public String searchTrailerByTitle(Model model,
        @RequestParam(value = "tl", required = false) String tl) {

        model.addAttribute("activeSession", true);

        if (tl != null) {
            Response<E_Trailer> searchTrailer = trailerInterface.getTrailersByTitle(tl);

            if (searchTrailer.getState()) {
                model.addAttribute("title", title + " | Búsqueda: " + tl);
                model.addAttribute("trailersList", searchTrailer.getListData());
                model.addAttribute("response", searchTrailer.getMessage());
                return "searchPage";

            } else {
                model.addAttribute("title", title + " | Error al realizar la búsqueda");
                model.addAttribute("status", "500");
                model.addAttribute("response", searchTrailer.getMessage());
                model.addAttribute("errorMessage", searchTrailer.getErrorMessage());
                return "error/500";
            }

        }

        return "searchPage";
    }


    @GetMapping("/filter/trailer")
    public String filterTrailer(Model model,
        @RequestParam(value = "gre", required = false) String gre,
        @RequestParam(value = "av", required = false) String av,
        @RequestParam(value = "sttl", required = false) String sttl) {

        model.addAttribute("activeSession", true);

        if (gre != null) {
            Response<E_Trailer> filterTrailer = trailerInterface.getTrailersByGenre(gre);

            if (filterTrailer.getState()) {
                model.addAttribute("title", title + " | Filtro: " + gre);
                model.addAttribute("trailersList", filterTrailer.getListData());
                model.addAttribute("response", filterTrailer.getMessage());

            } else {
                model.addAttribute("title", title + " | Error al realizar la búsqueda por género");
                model.addAttribute("status", "500");
                model.addAttribute("response", filterTrailer.getMessage());
                model.addAttribute("errorMessage", filterTrailer.getErrorMessage());
                return "error/500";
            }
        }

        if (av != null) {
            Response<E_Trailer> filterTrailer = trailerInterface.getTrailersByLanguage(av, true);

            if (filterTrailer.getState()) {
                model.addAttribute("title", title + " | Filtro: " + gre);
                model.addAttribute("trailersList", filterTrailer.getListData());
                model.addAttribute("response", filterTrailer.getMessage());

            } else {
                model.addAttribute("title", title + " | Error al realizar la búsqueda por género");
                model.addAttribute("status", "500");
                model.addAttribute("response", filterTrailer.getMessage());
                model.addAttribute("errorMessage", filterTrailer.getErrorMessage());
                return "error/500";
            }
        }

        if (sttl != null) {
            Response<E_Trailer> filterTrailer = trailerInterface.getTrailersByLanguage(sttl, false);

            if (filterTrailer.getState()) {
                model.addAttribute("title", title + " | Filtro: " + gre);
                model.addAttribute("trailersList", filterTrailer.getListData());
                model.addAttribute("response", filterTrailer.getMessage());

            } else {
                model.addAttribute("title", title + " | Error al realizar la búsqueda por género");
                model.addAttribute("status", "500");
                model.addAttribute("response", filterTrailer.getMessage());
                model.addAttribute("errorMessage", filterTrailer.getErrorMessage());
                return "error/500";
            }
        }

        Response<E_Language> languageResponse = languageInterface.getAllLanguages();
        if (languageResponse.getState()) {
            model.addAttribute("languagesList", languageResponse.getListData());
        } else {
            model.addAttribute("title", title + " | Error al obtener Lenguajes");
            model.addAttribute("status", "500");
            model.addAttribute("response", languageResponse.getMessage());
			model.addAttribute("errorMessage", languageResponse.getErrorMessage());
            return "error/500";
        }

        Response<E_Genre> genreResponse = genreInterface.getAllGenres();
        if (genreResponse.getState()) {
            model.addAttribute("genresList", genreResponse.getListData());
        } else {
            model.addAttribute("title", title + " | Error al obtener Generos");
            model.addAttribute("status", "500");
            model.addAttribute("response", genreResponse.getMessage());
			model.addAttribute("errorMessage", genreResponse.getErrorMessage());
            return "error/500";
        }

        Response<E_Country> countryResponse = countriesInterface.getAllCountries();
        if (countryResponse.getState()) {
            model.addAttribute("countriesList", countryResponse.getListData());
            return "trailers";
        } else {
            model.addAttribute("title", title + " | Error al obtener Paises");
            model.addAttribute("status", "500");
            model.addAttribute("response", countryResponse.getMessage());
			model.addAttribute("errorMessage", countryResponse.getErrorMessage());
            return "error/500";
        }

    }




    // Temporally function (whitout db) <=============================
    @PostMapping("/list/createNewList/{id}")
    public String createNewList(UserList usrList, @PathVariable String id) {
        System.out.println("*** ---> Lista creada");
        System.out.println(usrList.getName());

        // Redirect to same page
        return "redirect:/app/trailer/view/" + id;
    };

}
