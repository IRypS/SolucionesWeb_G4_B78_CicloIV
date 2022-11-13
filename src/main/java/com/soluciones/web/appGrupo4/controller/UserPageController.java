package com.soluciones.web.appGrupo4.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
   
    @GetMapping("/trailers")
    public String allTrailers(Model model) {

        // model.addAttribute("title", "Trailers | " + title);
        model.addAttribute("activeSession", true);

        Response<E_Trailer> trailerResponse = trailerInterface.getAllTrailers();
        Response<E_Language> languageResponse = languageInterface.getAllTrailers();
        Response<E_Genre> genreResponse = genreInterface.getAllGenres();
        Response<E_Country> countryResponse = countriesInterface.getAllCountries();

        if (trailerResponse.getState() && languageResponse.getState()) {
			model.addAttribute("title", title + " | Trailers");
			model.addAttribute("trailersList", trailerResponse.getListData());
			model.addAttribute("response", trailerResponse.getMessage());
            model.addAttribute("languagesList", languageResponse.getListData());
            model.addAttribute("genresList", genreResponse.getListData());
            model.addAttribute("countriesList", countryResponse.getListData());
			return "trailers";
		} else {

            List<String> errorsHeader = Arrays.asList(
                trailerResponse.getMessage(), 
                languageResponse.getMessage(), 
                genreResponse.getMessage(), 
                countryResponse.getMessage()
            );

            List<String> errorsBody = Arrays.asList(
                trailerResponse.getErrorMessage(), 
                languageResponse.getErrorMessage(), 
                genreResponse.getErrorMessage(), 
                countryResponse.getErrorMessage()
            );

			model.addAttribute("title", title + " | Error al obtener trailers");
			model.addAttribute("errorsHeader", errorsHeader);
			model.addAttribute("errorsBody", errorsBody);
			return "errors";
		}
    };

    @GetMapping("/trailer/view/{id}")
    public String trailerView(@PathVariable String id, Model model) {

        // model.addAttribute("title", "Trailer View | " + title);
        model.addAttribute("activeSession", true);

        Response<E_Trailer> trailerResponse = trailerInterface.getTargetTrailer(id);
        Response<E_Trailer> relatedTrailersResponse = trailerInterface.getRelatedTrailers(id);

        if (trailerResponse.getState() && relatedTrailersResponse.getState()) {
			model.addAttribute("title", title + " | " + trailerResponse.getData().getTitle());
			model.addAttribute("trailer", trailerResponse.getData());
            model.addAttribute("relatedTrailers", relatedTrailersResponse.getListData());
			model.addAttribute("response", trailerResponse.getMessage());
            // TemporallyData
            model.addAttribute("userList", listsInterface.getAllLists());

			return "trailer_view";
		} else {

            List<String> errorsHeader = Arrays.asList(
                trailerResponse.getMessage(),
                relatedTrailersResponse.getMessage()
            );

            List<String> errorsBody = Arrays.asList(
                trailerResponse.getErrorMessage(),
                relatedTrailersResponse.getErrorMessage()
            );

			model.addAttribute("title", title + " | Error al obtener el trailer");
			model.addAttribute("errorsHeader", errorsHeader);
			model.addAttribute("errorsBody", errorsBody);
			return "errors";
		}

        // model.addAttribute("trailer", targetTrailer);
        // model.addAttribute("relatedTrailers", trailerInterface.getRelatedTrailers(id));

        // return "trailer_view";
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
