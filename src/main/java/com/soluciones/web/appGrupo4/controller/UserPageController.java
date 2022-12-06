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

    @Value("${image.resource.path.general}" + "${image.folder.movie.name}" + "/")
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
            model.addAttribute("errorHeader", trailerResponsePaginated.getMessage());
			model.addAttribute("errorBody", trailerResponsePaginated.getErrorMessage());
			return "errors";
		}

        Response<E_Language> languageResponse = languageInterface.getAllLanguages();
        if (languageResponse.getState()) {
            model.addAttribute("languagesList", languageResponse.getListData());
        } else {
            model.addAttribute("title", title + " | Error al obtener Lenguajes");
            model.addAttribute("errorHeader", languageResponse.getMessage());
			model.addAttribute("errorBody", languageResponse.getErrorMessage());
            return "errors";
        }

        Response<E_Genre> genreResponse = genreInterface.getAllGenres();
        if (genreResponse.getState()) {
            model.addAttribute("genresList", genreResponse.getListData());
        } else {
            model.addAttribute("title", title + " | Error al obtener Generos");
            model.addAttribute("errorHeader", genreResponse.getMessage());
			model.addAttribute("errorBody", genreResponse.getErrorMessage());
            return "errors";
        }

        Response<E_Country> countryResponse = countriesInterface.getAllCountries();
        if (countryResponse.getState()) {
            model.addAttribute("countriesList", countryResponse.getListData());
            return "trailers";
        } else {
            model.addAttribute("title", title + " | Error al obtener Paises");
            model.addAttribute("errorHeader", countryResponse.getMessage());
			model.addAttribute("errorBody", countryResponse.getErrorMessage());
            return "errors";
        }
    };

    @GetMapping("/trailer/view/{id}")
    public String trailerView(@PathVariable String id, Model model) {

        model.addAttribute("activeSession", true);
        model.addAttribute("movieImagePath", movieImagePath);

        Response<E_Trailer> trailerResponse = trailerInterface.getTrailerById(id);
        if (trailerResponse.getState()) {
            model.addAttribute("title", title + " | " + trailerResponse.getData().getTitle());
			model.addAttribute("trailer", trailerResponse.getData());
            model.addAttribute("response", trailerResponse.getMessage());
        } else {
            model.addAttribute("title", title + " | Error al obtener el Trailer | ID:" + id);
            model.addAttribute("errorHeader", trailerResponse.getMessage());
			model.addAttribute("errorBody", trailerResponse.getErrorMessage());

            return "errors";
        }

        Response<E_Trailer> relatedTrailersResponse = trailerInterface.getRelatedTrailers(id);
        if (relatedTrailersResponse.getState()) {
            model.addAttribute("relatedTrailers", relatedTrailersResponse.getListData());
            // TemporallyData
            model.addAttribute("userList", listsInterface.getAllLists());

			return "trailer_view";

		} else {
            model.addAttribute("title", title + " | Error al obtener los trailers relacionados");
            model.addAttribute("errorHeader", relatedTrailersResponse.getMessage());
			model.addAttribute("errorBody", relatedTrailersResponse.getErrorMessage());
			return "errors";
		}
    };





    // Temporally function (whitout db) <=============================
    @PostMapping("/list/createNewList/{id}")
    public String createNewList(UserList usrList, @PathVariable String id) {
        System.out.println("*** ---> Lista creada");
        System.out.println(usrList.getName());

        // Redirect to same page
        return "redirect:/app/trailer/view/" + id;
    };

}
